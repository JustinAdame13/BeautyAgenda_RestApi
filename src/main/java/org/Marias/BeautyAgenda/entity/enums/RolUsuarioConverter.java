package org.Marias.BeautyAgenda.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RolUsuarioConverter implements AttributeConverter<RolUsuario, String> {

    @Override
    public String convertToDatabaseColumn(RolUsuario rol) {
        return rol == null ? null : rol.name().toLowerCase();
    }
    @Override
    public RolUsuario convertToEntityAttribute(String valor) {
        return valor == null ? null : RolUsuario.valueOf(valor.toUpperCase());
    }
}
