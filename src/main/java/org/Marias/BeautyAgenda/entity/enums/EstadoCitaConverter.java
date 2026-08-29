package org.Marias.BeautyAgenda.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EstadoCitaConverter  implements AttributeConverter<EstadoCita, String> {
    @Override
    public String convertToDatabaseColumn(EstadoCita estadoCita) {
        return estadoCita == null ? null : estadoCita.name().toLowerCase();
    }
    @Override
    public EstadoCita convertToEntityAttribute(String estadoCita) {
        return estadoCita == null ? null : EstadoCita.valueOf(estadoCita.toUpperCase());
    }

}
