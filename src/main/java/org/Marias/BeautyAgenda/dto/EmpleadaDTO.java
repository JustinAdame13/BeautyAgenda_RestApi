package org.Marias.BeautyAgenda.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadaDTO {

    private Long id;
    //Foreign key
    private Long idUsuario;

    private String nombre;

    private boolean activo;
}
