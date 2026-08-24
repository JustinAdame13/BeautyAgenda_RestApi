package org.Marias.BeautyAgenda.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;

@Converter
public class TipoServicioConverter implements AttributeConverter<TipoServicio, String> {

    @Override
    public String convertToDatabaseColumn(TipoServicio tipo) {
        return tipo == null ? null : tipo.name().toLowerCase();
    }
    @Override
    public TipoServicio convertToEntityAttribute(String valor) {
        return valor == null ? null : TipoServicio.valueOf(valor.toUpperCase());
    }

}
