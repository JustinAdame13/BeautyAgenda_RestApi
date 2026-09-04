package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
