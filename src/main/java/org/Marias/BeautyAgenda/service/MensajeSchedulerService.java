package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.dto.MensajeRequestDTO;
import org.Marias.BeautyAgenda.entity.Clienta;
import org.Marias.BeautyAgenda.entity.Mensaje;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;
import org.Marias.BeautyAgenda.repository.ClientaRepository;
import org.Marias.BeautyAgenda.repository.MensajeRepository;
import org.Marias.BeautyAgenda.repository.PlantillaRepository;
import org.Marias.BeautyAgenda.whatsapp.WhatsAppApiException;
import org.Marias.BeautyAgenda.whatsapp.WhatsAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MensajeSchedulerService {

    private static final ZoneId ZONA_NEGOCIO = ZoneId.of("America/Mexico_City");
    private static final String CODIGO_IDIOMA = "es_MX";

    @Autowired
    private MensajeRepository mensajeRepo;
    @Autowired
    private WhatsAppClient whatsAppClient;

    @Autowired
    private ClientaRepository clientaRepo;
    @Autowired
    private PlantillaRepository plantillaRepo;
    @Autowired
    private MensajeService mensajeService;

//    @Scheduled(cron = "0 0 9 * * *", zone = "America/Mexico_City")
    @Transactional
    public void generarMensajesDeCumpleanos() {

        Optional<PlantillaMensaje> plantillaCumple = plantillaRepo.findByTipo(TipoPlantilla.CUMPLE);
        if (plantillaCumple.isEmpty()) {
            return;
        }
        PlantillaMensaje plantilla = plantillaCumple.get();

        LocalDate hoy = LocalDate.now(ZONA_NEGOCIO);
        LocalDate fechaCumple = hoy.plusDays(2);

        List<Clienta> cumpleaneras = clientaRepo.findCumpleanerasEnFecha(
                fechaCumple.getMonthValue(), fechaCumple.getDayOfMonth());

        for (Clienta clienta : cumpleaneras) {

            if (!clienta.isMarketing()) {
                continue;
            }

            boolean yaExiste = mensajeRepo.existsByClientaIdAndPlantillaIdAndEstadoAndFechaProgramada(
                    clienta.getId(), plantilla.getId(), EstadoMensaje.PROGRAMADO, hoy);

            if (yaExiste) {
                continue;
            }

            MensajeRequestDTO dto = new MensajeRequestDTO(
                    clienta.getId(),
                    null,
                    plantilla.getId(),
                    hoy, // se envía hoy, aunque el cumpleaños sea en 2 días
                    Map.of("nombre", clienta.getNombre())
            );

            mensajeService.save(dto);
        }
    }

//    @Scheduled(cron = "0 0 20 * * *", zone = "America/Mexico_City")
    @Transactional
    public void enviarMensajesDelDia() {
        LocalDate hoy = LocalDate.now(ZONA_NEGOCIO);
        List<Mensaje> pendientes = mensajeRepo.findByEstadoAndFechaProgramada(EstadoMensaje.PROGRAMADO, hoy);

        for (Mensaje mensaje : pendientes) {
            procesarEnvio(mensaje);
        }
    }

//    @Scheduled(cron = "0 0 21 * * *", zone = "America/Mexico_City")
    @Transactional
    public void reintentarFallidos() {
        LocalDate hoy = LocalDate.now(ZONA_NEGOCIO);
        List<Mensaje> fallidos = mensajeRepo.findByEstadoAndFechaProgramada(EstadoMensaje.FALLIDO, hoy);

        // Nota: aquí se reintentan TODOS los fallidos del día, incluyendo los que
        // fallaron por causas permanentes (ej. número inválido). Es una decisión
        // consciente: no distinguimos ese caso para mantener el modelo simple,
        // dado el bajo volumen de mensajes del negocio.
        for (Mensaje mensaje : fallidos) {
            procesarEnvio(mensaje);
        }
    }

    private void procesarEnvio(Mensaje mensaje) {
        List<String> parametrosOrdenados = mensaje.getPlantilla().getOrdenParametros().stream()
                .map(clave -> mensaje.getParametros().getOrDefault(clave, ""))
                .toList();

        try {
            whatsAppClient.enviarPlantilla(
                    mensaje.getClienta().getTelefono(),
                    mensaje.getPlantilla().getNombreMeta(),
                    CODIGO_IDIOMA,
                    parametrosOrdenados
            );

            mensaje.setEstado(EstadoMensaje.ENVIADO);
            mensaje.setFechaEnvio(LocalDateTime.now(ZONA_NEGOCIO));

        } catch (WhatsAppApiException e) {
            mensaje.setEstado(EstadoMensaje.FALLIDO);
            mensaje.setIntentos(mensaje.getIntentos() + 1);
            System.err.println("Fallo al enviar mensaje id=" + mensaje.getId() +
                    " (reintentable=" + e.isReintentable() + "): " + e.getMessage());

        } catch (Exception e) {
            // Cualquier otra falla no anticipada: tratamos como reintentable por defecto
            mensaje.setEstado(EstadoMensaje.FALLIDO);
            mensaje.setIntentos(mensaje.getIntentos() + 1);
            System.err.println("Error inesperado al enviar mensaje id=" + mensaje.getId() + ": " + e.getMessage());
        }

        mensajeRepo.save(mensaje);
    }
}