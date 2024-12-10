package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_empleado_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String apellidos;

    @NotBlank
    @Email
    @Column(nullable = false, length = 255, unique = true)
    private String correo;

    @NotBlank
    @Column(nullable = false, length = 128)
    private String password;

    // Constructor por defecto
    public Empleado() {}

    // Getters y Setters
    // ...


    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank @Email String getCorreo() {
        return correo;
    }

    public void setCorreo(@NotBlank @Email String correo) {
        this.correo = correo;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}