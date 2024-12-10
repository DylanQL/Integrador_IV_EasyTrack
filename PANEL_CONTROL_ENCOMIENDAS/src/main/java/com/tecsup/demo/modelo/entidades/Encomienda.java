package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "encomienda")
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_encomienda_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_remitente_id", nullable = false)
    private Cliente remitente;

    @ManyToOne
    @JoinColumn(name = "fk_destinatario_id", nullable = false)
    private Cliente destinatario;

    @ManyToOne
    @JoinColumn(name = "fk_vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "fk_terminal_partida_id", nullable = false)
    private Terminal terminalPartida;

    @ManyToOne
    @JoinColumn(name = "fk_terminal_destino_id", nullable = false)
    private Terminal terminalDestino;

    @Column(nullable = false)
    private Double volumen;

    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 50)
    private String condicionEnvio;

    @Column(nullable = false)
    private Integer cantidadPaquetes;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "fk_empleado_registro_id", nullable = false)
    private Empleado empleadoRegistro;

    @ManyToOne
    @JoinColumn(name = "fk_empleado_entrega_id")
    private Empleado empleadoEntrega;

    // Constructor por defecto
    public Encomienda() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters
    // ...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleadoEntrega() {
        return empleadoEntrega;
    }

    public void setEmpleadoEntrega(Empleado empleadoEntrega) {
        this.empleadoEntrega = empleadoEntrega;
    }

    public Empleado getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(Empleado empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getCantidadPaquetes() {
        return cantidadPaquetes;
    }

    public void setCantidadPaquetes(Integer cantidadPaquetes) {
        this.cantidadPaquetes = cantidadPaquetes;
    }

    public String getCondicionEnvio() {
        return condicionEnvio;
    }

    public void setCondicionEnvio(String condicionEnvio) {
        this.condicionEnvio = condicionEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Terminal getTerminalDestino() {
        return terminalDestino;
    }

    public void setTerminalDestino(Terminal terminalDestino) {
        this.terminalDestino = terminalDestino;
    }

    public Terminal getTerminalPartida() {
        return terminalPartida;
    }

    public void setTerminalPartida(Terminal terminalPartida) {
        this.terminalPartida = terminalPartida;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}