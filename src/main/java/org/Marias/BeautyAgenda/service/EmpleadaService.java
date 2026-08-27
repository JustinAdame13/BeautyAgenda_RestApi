package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.EmpleadaMapper;
import org.Marias.BeautyAgenda.dto.EmpleadaDTO;
import org.Marias.BeautyAgenda.dto.EmpleadaRequestDTO;
import org.Marias.BeautyAgenda.entity.Empleada;
import org.Marias.BeautyAgenda.entity.Usuario;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.EmpleadaRepository;
import org.Marias.BeautyAgenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadaService {
    @Autowired
    private EmpleadaRepository empleadaRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;
    //metodo para encontar todas las empleadas
    public List<EmpleadaDTO> findAll(){
        return empleadaRepo.findAll().stream().map(EmpleadaMapper::toDTO)
                .collect(Collectors.toList());
    }
    //metodo para encontrar por id
    public EmpleadaDTO findbyId(Long id){
        Empleada empleada = empleadaRepo.findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException("Empleada no encontrada"));
    return EmpleadaMapper.toDTO(empleada);
    }
    //metodo para guardar empleadas
    public EmpleadaDTO save(EmpleadaRequestDTO dto){
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(()->new EntidadNoEncontradaException("Usuario no encontrado"));
        Empleada empleada = EmpleadaMapper.RqToEntity(dto, usuario);
        return EmpleadaMapper.toDTO(empleadaRepo.save(empleada));
    }
    //metodo para actualizar empleadas
    public EmpleadaDTO update(EmpleadaRequestDTO dto, Long id){
        Optional<Empleada> existe = empleadaRepo.findById(id);
        if (existe.isPresent()) {
            Empleada empleada = existe.get();

            empleada.setNombre(dto.getNombre());
            empleada.setActivo(dto.isActivo());
            return EmpleadaMapper.toDTO(empleadaRepo.save(empleada));
        }
        else{
            throw new EntidadNoEncontradaException("Empleada no encontrada");
        }
    }
    //metodo para borrar empleadas
    public void delete(Long id){
        if (empleadaRepo.findById(id).isPresent()) {
            empleadaRepo.deleteById(id);
        }
        else {
            throw new EntidadNoEncontradaException("Empleada no encontrada");
        }
    }

}
