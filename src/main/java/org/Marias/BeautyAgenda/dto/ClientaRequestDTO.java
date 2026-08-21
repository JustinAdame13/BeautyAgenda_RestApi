package org.Marias.BeautyAgenda.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class ClientaRequestDTO {


    private String nombre;
    private String telefono;
    private java.time.LocalDate fechaNacimiento;
    private boolean Recordatorios;
    private boolean marketing;
    private String notas;


    //constructor
    public ClientaRequestDTO( String nombre,
                      String telefono, java.time.LocalDate fechaNacimiento,
                      boolean recordatorios, boolean marketing, String notas) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        Recordatorios = recordatorios;
        this.marketing = marketing;
        this.notas = notas;
    }
    //getters y setters


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

    public java.time.LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.time.LocalDate fechaNacimiento) {
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

}
