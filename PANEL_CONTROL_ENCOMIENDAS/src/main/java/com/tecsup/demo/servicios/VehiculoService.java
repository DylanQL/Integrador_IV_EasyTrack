package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Vehiculo;
import java.util.List;

public interface VehiculoService {
    List<Vehiculo> listar();
    void grabar(Vehiculo vehiculo);
    Vehiculo buscar(Integer id);
    void eliminar(Integer id);
}