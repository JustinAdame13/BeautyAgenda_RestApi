package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantillaRepository extends JpaRepository<PlantillaMensaje, Long> {
    Optional<PlantillaMensaje> findByTipo(TipoPlantilla tipo);
}
