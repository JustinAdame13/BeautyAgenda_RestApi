package org.Marias.BeautyAgenda.dto;


import java.time.LocalDateTime;

public class ClientaDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private java.time.LocalDate fechaNacimiento;
    private boolean recordatorios;
    private boolean marketing;
    private String notas;
    private LocalDateTime fechaRegistro;

    //constructor
    public ClientaDTO(Long id, LocalDateTime fechaRegistro, String nombre,
                      String telefono, java.time.LocalDate fechaNacimiento,
                      boolean recordatorios, boolean marketing, String notas) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.recordatorios = recordatorios;
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
        return recordatorios;
    }

    public void setRecordatorios(boolean recordatorios) {
        this.recordatorios = recordatorios;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
