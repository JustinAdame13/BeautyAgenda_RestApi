package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.entity.Clienta;

public class ClientaMapper {

    public static ClientaDTO toDTO(Clienta clienta){
        return new ClientaDTO(clienta.getId(), clienta.getFechaRegistro(), clienta.getNombre(),
                clienta.getTelefono(),clienta.getFechaNacimiento(),clienta.isRecordatorios(),clienta.isMarketing(),
                clienta.getNotas());


    }
    public static Clienta toEntity(ClientaDTO clientaDTO){
        Clienta clienta = new Clienta();
        clienta.setId(clientaDTO.getId());
        clienta.setFechaRegistro(clientaDTO.getFechaRegistro());
        clienta.setNombre(clientaDTO.getNombre());
        clienta.setTelefono(clientaDTO.getTelefono());
        clienta.setFechaNacimiento(clientaDTO.getFechaNacimiento());
        clienta.setRecordatorios(clientaDTO.isRecordatorios());
        clienta.setMarketing(clientaDTO.isMarketing());
        clienta.setNotas(clientaDTO.getNotas());
        return clienta;
    }
}
