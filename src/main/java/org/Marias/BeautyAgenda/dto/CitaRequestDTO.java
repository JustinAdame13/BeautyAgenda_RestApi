package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaRequestDTO {

    private Long idClienta;
    private Long idEmpleada;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private EstadoCita estado;
    private String notas;
    private List<CitaServicioRequestDTO> servicios;
}
