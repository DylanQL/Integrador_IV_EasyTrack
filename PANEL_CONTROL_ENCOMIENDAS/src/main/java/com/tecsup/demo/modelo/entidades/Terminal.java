package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "terminal")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_terminal_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String direccion;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String ubicacion;

    // Constructor por defecto
    public Terminal() {}

    // Getters y Setters
    // ...


    public @NotBlank String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(@NotBlank String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public @NotBlank String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank String direccion) {
        this.direccion = direccion;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}