package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.EncomiendaRepository;
import com.tecsup.demo.modelo.entidades.Encomienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EncomiendaServiceImpl implements EncomiendaService {

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Encomienda> listar() {
        return encomiendaRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Encomienda encomienda) {
        encomiendaRepository.save(encomienda);
    }

    @Override
    @Transactional(readOnly = true)
    public Encomienda buscar(Integer id) {
        return encomiendaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        encomiendaRepository.deleteById(id);
    }
}