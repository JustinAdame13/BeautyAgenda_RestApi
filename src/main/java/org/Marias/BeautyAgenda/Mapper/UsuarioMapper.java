package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.UsuarioDTO;
import org.Marias.BeautyAgenda.dto.UsuarioRequestDTO;
import org.Marias.BeautyAgenda.entity.Usuario;


public class UsuarioMapper {



    public static UsuarioDTO toDTO (Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getUsername(),
                usuario.getRol(), usuario.getActivo());
    }
    public static Usuario RqToEntity (UsuarioRequestDTO dto, String passwordHash){
        return new Usuario(null, dto.getUsername(),
                passwordHash, dto.getRol(),dto.getActivo());
    }

}
