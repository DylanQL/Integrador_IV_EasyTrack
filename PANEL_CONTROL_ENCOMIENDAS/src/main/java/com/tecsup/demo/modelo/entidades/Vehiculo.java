package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_vehiculo_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String placaVehiculo;

    @NotBlank
    @Column(nullable = false)
    private String estadoVehiculo;

    // Constructor por defecto
    public Vehiculo() {
        this.estadoVehiculo = "Dentro de terminal";
    }

    // Getters y Setters
    // ...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(@NotBlank String estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public @NotBlank String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(@NotBlank String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
}