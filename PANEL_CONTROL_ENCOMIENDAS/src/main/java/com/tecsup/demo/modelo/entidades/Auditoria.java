package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tabla;

    @Column(name = "id_registro")
    private Integer idRegistro;

    private Date fecha;

    private String usuario;

    private String tipo;

    // Constructors, Getters, Setters, toString()
    public Auditoria() {}

    public Auditoria(String tabla, Integer idRegistro, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    // Getters and Setters
    // ...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }
}
