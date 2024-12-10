package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Contacto;
import java.util.List;

public interface ContactoService {
    List<Contacto> listar();
    void grabar(Contacto contacto);
    Contacto buscar(Integer id);
    void eliminar(Integer id);
}