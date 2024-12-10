package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Seguridad;
import java.util.List;

public interface SeguridadService {
    List<Seguridad> listar();
    void grabar(Seguridad seguridad);
    Seguridad buscar(Integer id);
    void eliminar(Integer id);
}