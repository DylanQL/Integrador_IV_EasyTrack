package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_contacto_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String asunto;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // Constructor por defecto
    public Contacto() {
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    // ...


    public @NotBlank String getAsunto() {
        return asunto;
    }

    public void setAsunto(@NotBlank String asunto) {
        this.asunto = asunto;
    }

    public @NotBlank String getMensaje() {
        return mensaje;
    }

    public void setMensaje(@NotBlank String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}