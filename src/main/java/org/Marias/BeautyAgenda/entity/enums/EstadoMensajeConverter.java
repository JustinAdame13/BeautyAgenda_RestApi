package org.Marias.BeautyAgenda.entity.enums;

import jakarta.persistence.AttributeConverter;

public class EstadoMensajeConverter implements AttributeConverter<EstadoMensaje, String> {

    @Override
    public String convertToDatabaseColumn(EstadoMensaje estado ) {
        return estado == null ? null : estado.name().toLowerCase();
    }
    @Override
    public EstadoMensaje convertToEntityAttribute(String valor) {
        return valor == null ? null : EstadoMensaje.valueOf(valor.toUpperCase());
    }
}
