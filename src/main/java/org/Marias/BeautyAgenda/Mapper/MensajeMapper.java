package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.MensajeDTO;
import org.Marias.BeautyAgenda.dto.MensajeRequestDTO;
import org.Marias.BeautyAgenda.entity.Cita;
import org.Marias.BeautyAgenda.entity.Clienta;
import org.Marias.BeautyAgenda.entity.Mensaje;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;

public class MensajeMapper {

    public static MensajeDTO toDTO (Mensaje mensaje) {
        Long idCita = mensaje.getCita() != null ? mensaje.getCita().getId() : null;
        return new MensajeDTO(mensaje.getId(),
                mensaje.getClienta().getId(),
                idCita,
                mensaje.getPlantilla().getId(),
                mensaje.getFechaProgramada(),
                mensaje.getEstado(),
                mensaje.getIntentos(),
                mensaje.getParametros(),
                mensaje.getFechaEnvio());
    }
    public static Mensaje rqToEntity (MensajeRequestDTO dto, Clienta clienta, Cita cita, PlantillaMensaje plantilla) {

        return new Mensaje(null,
                           clienta,
                            cita,
                            plantilla,
                            dto.getFechaProgramada(),
                            EstadoMensaje.PROGRAMADO,
                            0,
                            dto.getParametros(),
                            null);
    }
}
