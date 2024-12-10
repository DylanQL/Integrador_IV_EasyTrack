package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Encomienda;
import java.util.List;

public interface EncomiendaService {
    List<Encomienda> listar();
    void grabar(Encomienda encomienda);
    Encomienda buscar(Integer id);
    void eliminar(Integer id);
}