package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.TerminalRepository;
import com.tecsup.demo.modelo.entidades.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Terminal> listar() {
        return terminalRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Terminal terminal) {
        terminalRepository.save(terminal);
    }

    @Override
    @Transactional(readOnly = true)
    public Terminal buscar(Integer id) {
        return terminalRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        terminalRepository.deleteById(id);
    }
}