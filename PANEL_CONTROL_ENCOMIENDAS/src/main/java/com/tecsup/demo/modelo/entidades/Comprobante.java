package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobante")
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_comprobante_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "fk_encomienda_id", nullable = false)
    private Encomienda encomienda;

    @Column(nullable = false, length = 50)
    private String estadoPago;

    @Column(nullable = false)
    private Double monto;

    private LocalDateTime fechaPago;

    // Constructor por defecto
    public Comprobante() {}

    // Getters y Setters
    // ...


    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}