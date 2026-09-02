package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.TipoServicio;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioRequestDTO {

    private String nombre;
    private Integer duracion;
    private BigDecimal precio;
    private TipoServicio tipo;
    private String descripcion;
    private List<Long>  idsPlantillas;

}
