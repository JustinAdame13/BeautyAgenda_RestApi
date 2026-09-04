package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {

    private Long id;
    private Long idClienta;
    private Long idCita;
    private Long idPlantilla;
    private LocalDate fechaProgramada;
    private EstadoMensaje estado;
    private Integer intentos;
    private Map<String, String> parametros;
    private LocalDateTime fechaEnvio;

}
