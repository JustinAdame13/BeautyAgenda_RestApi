package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "clienta")
@NoArgsConstructor
@AllArgsConstructor
public class Clienta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    @Column(name = "telefono", nullable = false, length = 20, unique = true)
    private String telefono;
    @Column(name = "fecha_nacimiento")
    private java.time.LocalDate fechaNacimiento;
    @Column(name = "opt_in_recordatorios", nullable = false)
    private boolean recordatorios;
    @Column(name = "opt_in_marketing", nullable = false)
    private boolean marketing;
    @Column(name = "notas")
    private String notas;
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;



}
