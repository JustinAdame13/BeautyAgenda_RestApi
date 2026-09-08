package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.Mensaje;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByEstado(EstadoMensaje estado);
    List<Mensaje> findByClientaId(Long idClienta);
}
