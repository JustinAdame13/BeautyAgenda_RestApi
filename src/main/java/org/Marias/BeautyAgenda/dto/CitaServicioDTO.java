package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaServicioDTO {
    private Long id;
    private Long idServicio;
    private BigDecimal precioCobrado;
}
