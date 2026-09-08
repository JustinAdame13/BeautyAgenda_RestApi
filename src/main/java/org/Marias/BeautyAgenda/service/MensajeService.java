package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.MensajeMapper;
import org.Marias.BeautyAgenda.dto.MensajeDTO;
import org.Marias.BeautyAgenda.dto.MensajeRequestDTO;
import org.Marias.BeautyAgenda.dto.MensajeUpdateDTO;
import org.Marias.BeautyAgenda.entity.*;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.CitaRepository;
import org.Marias.BeautyAgenda.repository.ClientaRepository;
import org.Marias.BeautyAgenda.repository.MensajeRepository;
import org.Marias.BeautyAgenda.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepo;

    @Autowired
    private ClientaRepository clientaRepo;

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private PlantillaRepository plantillaRepo;

    //metodo para buscar todos los mensajes
    @Transactional(readOnly = true)
    public List<MensajeDTO> findAll() {
        return mensajeRepo.findAll().stream().map(MensajeMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar mensaje por id
    @Transactional(readOnly = true)
    public MensajeDTO findById(Long id) {
        Mensaje mensaje = mensajeRepo.findById(id).orElseThrow(()->new EntidadNoEncontradaException("Mensaje no encontrado"));
        return MensajeMapper.toDTO(mensaje);
    }
    //metodo para buscar por estado
    @Transactional(readOnly = true)
    public List<MensajeDTO> findByEstado(EstadoMensaje estado){
        return mensajeRepo.findByEstado(estado).stream()
                .map(MensajeMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por idClienta
    @Transactional(readOnly = true)
    public List<MensajeDTO> findByClienta(Long id){
        return mensajeRepo.findByClientaId(id).stream()
                .map(MensajeMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para guardar mensaje nuevo
    @Transactional
    public MensajeDTO save(MensajeRequestDTO dto) {

        Clienta clienta = clientaRepo.findById(dto.getIdClienta()).orElseThrow(()-> new EntidadNoEncontradaException("clienta no encontrada"));
        Cita cita = null;
        if(dto.getIdCita() != null) {
            cita = citaRepo.findById(dto.getIdCita()).orElseThrow(() -> new EntidadNoEncontradaException("cita no encontrada"));
        }

        PlantillaMensaje plantilla = plantillaRepo.findById(dto.getIdPlantilla()).orElseThrow(()-> new EntidadNoEncontradaException("plantilla no encontrada"));

        return MensajeMapper.toDTO(mensajeRepo.save(MensajeMapper.rqToEntity(dto,clienta,cita,plantilla)));
    }
    //metodo para actualizar un mensaje por su id
    @Transactional
    public MensajeDTO update(MensajeUpdateDTO dto, Long id){
        Optional<Mensaje> existe = mensajeRepo.findById(id);
         if (existe.isPresent()){
             Mensaje mensaje = existe.get();

             mensaje.setEstado(dto.getEstado());
             mensaje.setIntentos(dto.getIntentos());
             mensaje.setFechaEnvio(dto.getFechaEnvio());

             return MensajeMapper.toDTO(mensajeRepo.save(mensaje));
         }
         else{
             throw new EntidadNoEncontradaException("no existe el mensaje");
         }

    }
    //metodo para borrar un mensaje por su id
    public void delete(Long id){
        if(mensajeRepo.findById(id).isPresent()){
            mensajeRepo.deleteById(id);
        }
        else{
            throw new EntidadNoEncontradaException("no se encontro el mensaje con ese id");
        }
    }



}
