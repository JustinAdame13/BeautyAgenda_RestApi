package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.ServicioMapper;
import org.Marias.BeautyAgenda.Mapper.UsuarioMapper;
import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.UsuarioDTO;
import org.Marias.BeautyAgenda.dto.UsuarioRequestDTO;
import org.Marias.BeautyAgenda.entity.Servicio;
import org.Marias.BeautyAgenda.entity.Usuario;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    //metodo para buscar todos los usuarios
    public List<UsuarioDTO> findAll(){
        return usuarioRepo.findAll().stream()
                .map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por id
    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Usuario no encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }
    //metodo para guardar
    public UsuarioDTO save(UsuarioRequestDTO dto){
        Usuario usuario = UsuarioMapper.RqToEntity(dto);
        return UsuarioMapper.toDTO(usuarioRepo.save(usuario));
    }
    //metodo para actualizar servicio
    public UsuarioDTO update(UsuarioRequestDTO dto, Long id){
        Optional<Usuario> existe = usuarioRepo.findById(id);
        if (existe.isPresent()) {
            Usuario usuario = existe.get();
            usuario.setUsaername(dto.getUsername());
            usuario.setPasswordHash(dto.getPassword());
            usuario.setRol(dto.getRol());
            usuario.setActivo(dto.getActivo());
            return UsuarioMapper.toDTO(usuarioRepo.save(usuario));
        }
        else {
            throw new EntidadNoEncontradaException("Usuario no encontrado");
        }
    }
    public void delete(Long id){
        if (usuarioRepo.findById(id).isPresent()) {
            usuarioRepo.deleteById(id);
        }
        else {
            throw new EntidadNoEncontradaException("Usuario no encontrado");
        }
    }
}
