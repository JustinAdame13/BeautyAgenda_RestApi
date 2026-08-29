package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.EstadoCita;
import org.Marias.BeautyAgenda.entity.enums.EstadoCitaConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clienta", nullable = false)
    private Clienta clienta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleada", nullable = false)
    private Empleada empleada;

    @Column(name = "inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "fin", nullable = false)
    private LocalDateTime fin;

    @Convert(converter = EstadoCitaConverter.class)
    @Column(name = "estado", nullable = false)
    private EstadoCita estado;

    @Column(name = "notas", length = 150)
    private String notas;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaServicio> citaServicio = new ArrayList<>();


}
