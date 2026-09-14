package org.Marias.BeautyAgenda.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WhatsAppTemplateRequest(
        @JsonProperty("messaging_product") String messagingProduct,
        String to,
        String type,
        WhatsAppTemplate template
) {}

record WhatsAppTemplate(
        String name,
        WhatsAppLanguage language,
        List<WhatsAppComponent> components
) {}

record WhatsAppLanguage(String code) {}

record WhatsAppComponent(String type, List<WhatsAppParameter> parameters) {}

record WhatsAppParameter(String type, String text) {}