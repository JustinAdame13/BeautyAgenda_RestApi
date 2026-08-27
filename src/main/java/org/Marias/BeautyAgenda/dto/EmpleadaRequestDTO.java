package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadaRequestDTO {

    private Long idUsuario;

    private String nombre;

    private boolean activo;
}
