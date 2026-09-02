package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaDTO {

    private Long id;
    private TipoPlantilla tipo;
    private String nombreMeta;
    private List<Long> idsServicios;
}
