package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> listar();
    void grabar(Empleado empleado);
    Empleado buscar(Integer id);
    void eliminar(Integer id);
}