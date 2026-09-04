package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.MensajeMapper;
import org.Marias.BeautyAgenda.dto.MensajeDTO;
import org.Marias.BeautyAgenda.dto.MensajeRequestDTO;
import org.Marias.BeautyAgenda.entity.Mensaje;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;git aadgit
import org.Marias.BeautyAgenda.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepo;

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

    @Transactional
    public void save(MensajeRequestDTO dto) {

    }

}
