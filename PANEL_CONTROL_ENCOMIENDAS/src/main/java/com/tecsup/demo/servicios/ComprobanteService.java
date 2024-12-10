package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Comprobante;
import java.util.List;

public interface ComprobanteService {
    List<Comprobante> listar();
    void grabar(Comprobante comprobante);
    Comprobante buscar(Integer id);
    void eliminar(Integer id);
}