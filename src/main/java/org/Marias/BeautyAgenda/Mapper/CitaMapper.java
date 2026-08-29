package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.CitaDTO;
import org.Marias.BeautyAgenda.dto.CitaRequestDTO;
import org.Marias.BeautyAgenda.dto.CitaServicioDTO;
import org.Marias.BeautyAgenda.entity.*;
import org.Marias.BeautyAgenda.entity.enums.EstadoCita;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CitaMapper {

    public static CitaDTO toDTO(Cita cita) {
        List<CitaServicioDTO> citaServicioDTOS = cita.getCitaServicio().stream()
                    .map(CitaServicioMapper::toDTO).collect(Collectors.toList());
        return new CitaDTO(cita.getId(), cita.getClienta().getId(),cita.getEmpleada().getId(),
                cita.getInicio(),cita.getFin(),cita.getEstado(),cita.getNotas(),citaServicioDTOS);
    }

    public static Cita RqToEntity(CitaRequestDTO dto, Clienta clienta, Empleada empleada,
                                  Map<Long, Servicio> serviciosPorId) {

        Cita cita = new Cita();
        cita.setClienta(clienta);
        cita.setEmpleada(empleada);
        cita.setInicio(dto.getInicio());
        cita.setFin(dto.getFin());
        cita.setNotas(dto.getNotas());
        cita.setEstado(EstadoCita.CONFIRMADA);

        List<CitaServicio> citaServicios = dto.getServicios().stream()
                .map(dtoServicio -> CitaServicioMapper.RqtoEntity(
                        dtoServicio, cita, serviciosPorId.get(dtoServicio.getIdServicio())))
                .collect(Collectors.toList());

        cita.setCitaServicio(citaServicios);

        return cita;
    }
}
