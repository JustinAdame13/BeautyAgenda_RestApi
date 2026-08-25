package org.Marias.BeautyAgenda.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.RolUsuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {


    private String username;
    private String password;
    private RolUsuario rol;
    private Boolean activo;
}
