package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.Clienta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientaRepository extends JpaRepository<Clienta, Long> {
    Optional<Clienta> findByNombre(String nombre);
}
