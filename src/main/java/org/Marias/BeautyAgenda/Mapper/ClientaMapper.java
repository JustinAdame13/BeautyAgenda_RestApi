package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.dto.ClientaRequestDTO;
import org.Marias.BeautyAgenda.entity.Clienta;

import java.time.LocalDateTime;

public class ClientaMapper {
//metodo para convertir un entity en dto
    public static ClientaDTO toDTO(Clienta clienta){
        return new ClientaDTO(clienta.getId(), clienta.getFechaRegistro(), clienta.getNombre(),
                clienta.getTelefono(),clienta.getFechaNacimiento(),clienta.isRecordatorios(),clienta.isMarketing(),
                clienta.getNotas());


    }
    //metodo para convertir un dto en un entity
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
    //metodo para convertir un Requestdto en un entity
    public static Clienta RqToEntity(ClientaRequestDTO dto, LocalDateTime fechaRegistro) {
        Clienta clienta = new Clienta();
        clienta.setNombre(dto.getNombre());
        clienta.setTelefono(dto.getTelefono());
        clienta.setFechaNacimiento(dto.getFechaNacimiento());
        clienta.setRecordatorios(dto.isRecordatorios());
        clienta.setMarketing(dto.isMarketing());
        clienta.setNotas(dto.getNotas());
        clienta.setFechaRegistro(fechaRegistro);
        return clienta;
    }
}
