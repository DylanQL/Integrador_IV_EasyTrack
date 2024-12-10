package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Terminal;
import java.util.List;

public interface TerminalService {
    List<Terminal> listar();
    void grabar(Terminal terminal);
    Terminal buscar(Integer id);
    void eliminar(Integer id);
}