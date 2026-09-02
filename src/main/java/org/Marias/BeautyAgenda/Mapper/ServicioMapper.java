package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.ServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.entity.Servicio;

import java.util.Set;
import java.util.stream.Collectors;

public class ServicioMapper {

    public static ServicioDTO toDTO(Servicio servicio) {
        return new ServicioDTO(servicio.getId(),
                servicio.getNombre(),
                servicio.getDuracion(),
                servicio.getPrecio(),
                servicio.getTipo(),
                servicio.getDescripcion(),
                servicio.getPlantillas().stream().map(p->p.getId()).collect(Collectors.toList()));
    }


    public static Servicio RqToEntity(ServicioRequestDTO dto, Set<PlantillaMensaje>plantillas) {
        return new Servicio(null,
                dto.getNombre(),
                dto.getDuracion(),
                dto.getPrecio(),
                dto.getTipo(),
                dto.getDescripcion(),
                plantillas);
    }

}
