package org.Marias.BeautyAgenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaRequestDTO {

    private TipoPlantilla tipo;
    private String nombreMeta;

}

