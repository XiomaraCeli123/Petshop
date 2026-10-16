package com.dominio.PetShop.models;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class citaDto {

    // Campos relacionados con la cita

    @NotEmpty(message = "El nombre del dueño es requerido")
    private String nombreDueno;

    @NotEmpty(message = "El nombre de la mascota es requerido")
    private String nombreMascota;

    @Email(message = "El correo electrónico debe ser válido")
    private String correo;

    @NotEmpty(message = "El teléfono es requerido")
    private String telefono;

    @NotEmpty(message = "La dirección es requerida")
    private String direccion;

    @NotNull(message = "La fecha de la cita es requerida")
    private LocalDate fechaCita;

    @NotNull(message = "La hora de la cita es requerida")
    private LocalTime horaCita;

    @NotEmpty(message = "El tipo de servicio es requerido")
    private String servicio;

    private String observaciones;

    // Getters y Setters

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
