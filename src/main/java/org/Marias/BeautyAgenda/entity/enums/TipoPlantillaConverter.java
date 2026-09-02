package org.Marias.BeautyAgenda.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TipoPlantillaConverter implements AttributeConverter<TipoPlantilla, String> {

    @Override
    public String convertToDatabaseColumn(TipoPlantilla tipoPlantilla) {
        return tipoPlantilla == null ? null : tipoPlantilla.name().toLowerCase();
    }
    @Override
    public TipoPlantilla convertToEntityAttribute(String tipoPlantilla) {
        return tipoPlantilla == null ? null : TipoPlantilla.valueOf(tipoPlantilla.toUpperCase());
    }
}
