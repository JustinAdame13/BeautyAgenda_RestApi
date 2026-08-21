package org.Marias.BeautyAgenda.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class ClientaDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private LocalDateTime fechaNacimiento;
    private boolean Recordatorios;
    private boolean marketing;
    private String notas;
    private LocalDateTime fechaRegistro;

    //constructor
    public ClientaDTO(Long id, LocalDateTime fechaRegistro, String nombre, String telefono, LocalDateTime fechaNacimiento, boolean recordatorios, boolean marketing, String notas) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        Recordatorios = recordatorios;
        this.marketing = marketing;
        this.notas = notas;
    }
    //getters y setters
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public boolean isMarketing() {
        return marketing;
    }

    public void setMarketing(boolean marketing) {
        this.marketing = marketing;
    }

    public boolean isRecordatorios() {
        return Recordatorios;
    }

    public void setRecordatorios(boolean recordatorios) {
        Recordatorios = recordatorios;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
