package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "seguridad")
public class Seguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_seguridad_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "fk_encomienda_id", nullable = false)
    private Encomienda encomienda;

    @Column(nullable = false)
    private Boolean claveHabilitada;

    @Column(nullable = false, length = 128)
    private String claveEstatica;

    // Constructor por defecto
    public Seguridad() {
        this.claveHabilitada = false;
    }

    // Getters y Setters
    // ...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaveEstatica() {
        return claveEstatica;
    }

    public void setClaveEstatica(String claveEstatica) {
        this.claveEstatica = claveEstatica;
    }

    public Boolean getClaveHabilitada() {
        return claveHabilitada;
    }

    public void setClaveHabilitada(Boolean claveHabilitada) {
        this.claveHabilitada = claveHabilitada;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }
}