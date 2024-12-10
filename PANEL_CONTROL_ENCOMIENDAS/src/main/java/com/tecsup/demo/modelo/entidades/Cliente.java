package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_cliente_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 15)
    private String dni;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 15)
    private String telefono;

    // Constructor por defecto
    public Cliente() {}

    // Getters y Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NotBlank String apellidos) {
        this.apellidos = apellidos;
    }

    public @NotBlank String getNombres() {
        return nombres;
    }

    public void setNombres(@NotBlank String nombres) {
        this.nombres = nombres;
    }

    public @NotBlank String getDni() {
        return dni;
    }

    public void setDni(@NotBlank String dni) {
        this.dni = dni;
    }
}