package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.Clienta;
import org.Marias.BeautyAgenda.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository <Servicio, Long>{
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

}
