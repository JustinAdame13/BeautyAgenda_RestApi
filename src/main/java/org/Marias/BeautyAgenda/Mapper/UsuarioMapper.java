package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.UsuarioDTO;
import org.Marias.BeautyAgenda.dto.UsuarioRequestDTO;
import org.Marias.BeautyAgenda.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO (Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getUsaername(),
                usuario.getRol(), usuario.getActivo());
    }
    public static Usuario RqToEntity (UsuarioRequestDTO dto){
        return new Usuario(null, dto.getUsername(),
                dto.getPassword(), dto.getRol(),dto.getActivo());
    }

}
