package org.Marias.BeautyAgenda.whatsapp;

public class WhatsAppApiException extends RuntimeException {

    private final boolean reintentable;

    public WhatsAppApiException(String message, boolean reintentable) {
        super(message);
        this.reintentable = reintentable;
    }

    public boolean isReintentable() {
        return reintentable;
    }
}