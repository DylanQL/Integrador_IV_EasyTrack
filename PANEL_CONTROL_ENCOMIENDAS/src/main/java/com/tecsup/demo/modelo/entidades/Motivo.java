package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "motivo")
public class Motivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_motivo_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String descripcion;

    // Constructor por defecto
    public Motivo() {}

    // Getters y Setters
    // ...


    public @NotBlank String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}