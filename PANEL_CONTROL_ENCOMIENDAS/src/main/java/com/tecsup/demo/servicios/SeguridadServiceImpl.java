package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.SeguridadRepository;
import com.tecsup.demo.modelo.entidades.Seguridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeguridadServiceImpl implements SeguridadService {

    @Autowired
    private SeguridadRepository seguridadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Seguridad> listar() {
        return seguridadRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Seguridad seguridad) {
        seguridadRepository.save(seguridad);
    }

    @Override
    @Transactional(readOnly = true)
    public Seguridad buscar(Integer id) {
        return seguridadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        seguridadRepository.deleteById(id);
    }
}