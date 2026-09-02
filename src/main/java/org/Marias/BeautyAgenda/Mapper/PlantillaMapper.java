package org.Marias.BeautyAgenda.Mapper;

import org.Marias.BeautyAgenda.dto.PlantillaDTO;
import org.Marias.BeautyAgenda.dto.PlantillaRequestDTO;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;

import java.util.HashSet;
import java.util.stream.Collectors;

public class PlantillaMapper {

    public static PlantillaDTO toDTO (PlantillaMensaje plantilla){
        return new PlantillaDTO(plantilla.getId(), plantilla.getTipo(),
                plantilla.getNombreMeta(),
                plantilla.getServicios().stream().map(s -> s.getId()).collect(Collectors.toList()));
    }

    public static PlantillaMensaje toEntity (PlantillaRequestDTO dto){
     return new PlantillaMensaje(null, dto.getTipo(), dto.getNombreMeta(), new HashSet<>());
    }


}
