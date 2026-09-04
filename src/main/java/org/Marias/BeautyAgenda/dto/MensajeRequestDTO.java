package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {

    private Long idClienta;
    private Long idCita;
    private Long idPlantilla;
    private LocalDate fechaProgramada;
    private Map<String, String> parametros;

}
