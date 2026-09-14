package org.Marias.BeautyAgenda.whatsapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class WhatsAppClient {

    @Value("${app.whatsapp.enabled}")
    private boolean whatsappEnabled;

    @Value("${app.whatsapp.access-token}")
    private String accessToken;

    @Value("${app.whatsapp.phone-number-id}")
    private String phoneNumberId;

    @Value("${app.whatsapp.api-version}")
    private String apiVersion;

    private final RestClient restClient = RestClient.create();

    public void enviarPlantilla(String telefono, String nombrePlantilla,
                                String codigoIdioma, List<String> parametrosOrdenados) {

        if (!whatsappEnabled) {
            System.out.println("[SIMULADO] WhatsApp a " + telefono +
                    " | plantilla: " + nombrePlantilla + " | params: " + parametrosOrdenados);
            return;
        }

        List<WhatsAppParameter> parametros = parametrosOrdenados.stream()
                .map(texto -> new WhatsAppParameter("text", texto))
                .toList();

        WhatsAppComponent bodyComponent = new WhatsAppComponent("body", parametros);

        WhatsAppTemplateRequest request = new WhatsAppTemplateRequest(
                "whatsapp",
                telefono,
                "template",
                new WhatsAppTemplate(nombrePlantilla, new WhatsAppLanguage(codigoIdioma), List.of(bodyComponent))
        );

        try {
            restClient.post()
                    .uri("https://graph.facebook.com/{version}/{phoneId}/messages", apiVersion, phoneNumberId)
                    .header("Authorization", "Bearer " + accessToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

        } catch (HttpClientErrorException.TooManyRequests e) {
            // 429: alcanzaste el límite de tasa de Meta -> vale la pena reintentar más tarde
            throw new WhatsAppApiException("Límite de tasa alcanzado en Meta", true);

        } catch (HttpClientErrorException e) {
            // Resto de 4xx: número inválido, plantilla no existe/no aprobada, parámetros mal formados
            // Reintentar no va a arreglar nada de esto
            throw new WhatsAppApiException("Meta rechazó el mensaje: " + e.getResponseBodyAsString(), false);

        } catch (HttpServerErrorException e) {
            // 5xx: problema del lado de Meta, no tuyo -> sí vale la pena reintentar
            throw new WhatsAppApiException("Error del servidor de Meta", true);

        } catch (ResourceAccessException e) {
            // timeout, problema de red -> reintentable
            throw new WhatsAppApiException("No se pudo conectar con Meta", true);
        }
    }
}