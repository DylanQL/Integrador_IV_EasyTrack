package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listar();
    void grabar(Cliente cliente);
    Cliente buscar(Integer id);
    void eliminar(Integer id);
}