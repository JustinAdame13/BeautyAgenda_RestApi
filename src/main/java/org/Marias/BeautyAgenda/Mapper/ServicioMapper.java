package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.ServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.Servicio;

public class ServicioMapper {

    public static ServicioDTO toDTO(Servicio servicio) {
        return new ServicioDTO(servicio.getId(), servicio.getNombre(),
                servicio.getDuracion(), servicio.getPrecio(),servicio.getTipo(), servicio.getDescripcion());
    }

    public static Servicio toEntity(ServicioDTO dto) {
        return new Servicio(dto.getId(), dto.getNombre(),
                dto.getDuracion(), dto.getPrecio(),dto.getTipo(), dto.getDescripcion());
    }

    public static Servicio RqToEntity(ServicioRequestDTO dto) {
        return new Servicio(null, dto.getNombre(),
                dto.getDuracion(), dto.getPrecio(),dto.getTipo(), dto.getDescripcion());
    }

}
