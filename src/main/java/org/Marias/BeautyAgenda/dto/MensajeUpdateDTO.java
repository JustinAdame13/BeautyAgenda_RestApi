package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeUpdateDTO {
    private EstadoMensaje estado;
    private Integer intentos;
    private LocalDateTime fechaEnvio;
}
