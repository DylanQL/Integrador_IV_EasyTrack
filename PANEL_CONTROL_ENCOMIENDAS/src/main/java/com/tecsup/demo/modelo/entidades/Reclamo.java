package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_reclamo_id")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_encomienda_id")
    private Encomienda encomienda;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_motivo_id")
    private Motivo motivo;

    @Column(name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    // Default Constructor
    public Reclamo() {}

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}