package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.EmpleadaDTO;
import org.Marias.BeautyAgenda.dto.EmpleadaRequestDTO;
import org.Marias.BeautyAgenda.entity.Empleada;
import org.Marias.BeautyAgenda.entity.Usuario;

public class EmpleadaMapper {

    public static EmpleadaDTO toDTO(Empleada empleada){
        return new EmpleadaDTO(empleada.getId(), empleada.getUsuario().getId(),
                empleada.getNombre(), empleada.isActivo());
    }

    public static Empleada RqToEntity(EmpleadaRequestDTO dto, Usuario usuario){
        return new Empleada(null, usuario, dto.getNombre(), dto.isActivo());
    }
}
