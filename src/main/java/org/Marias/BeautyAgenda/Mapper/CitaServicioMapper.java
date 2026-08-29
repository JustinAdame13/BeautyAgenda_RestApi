package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.CitaRequestDTO;
import org.Marias.BeautyAgenda.dto.CitaServicioDTO;
import org.Marias.BeautyAgenda.dto.CitaServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.Cita;
import org.Marias.BeautyAgenda.entity.CitaServicio;
import org.Marias.BeautyAgenda.entity.Servicio;

public class CitaServicioMapper {

    public static CitaServicioDTO toDTO(CitaServicio citaServicio){
        return new CitaServicioDTO(citaServicio.getId(),citaServicio
                        .getServicio().getId(),citaServicio.getPrecioCobrado());
    }
    public static CitaServicio RqtoEntity(CitaServicioRequestDTO dto, Cita cita, Servicio servicio){
        return new CitaServicio(null,cita,servicio,dto.getPrecioCobrado());
    }
}
