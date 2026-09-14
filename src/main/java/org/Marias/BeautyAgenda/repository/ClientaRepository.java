package org.Marias.BeautyAgenda.repository;

import org.Marias.BeautyAgenda.entity.Clienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientaRepository extends JpaRepository<Clienta, Long> {
    List<Clienta> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT c FROM Clienta c WHERE FUNCTION('date_part', 'month', c.fechaNacimiento) = :mes " +
            "AND FUNCTION('date_part', 'day', c.fechaNacimiento) = :dia")
    List<Clienta> findCumpleanerasEnFecha(@Param("mes") int mes, @Param("dia") int dia);
}
