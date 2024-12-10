package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.ComprobanteRepository;
import com.tecsup.demo.modelo.entidades.Comprobante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comprobante> listar() {
        return comprobanteRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Comprobante comprobante) {
        comprobanteRepository.save(comprobante);
    }

    @Override
    @Transactional(readOnly = true)
    public Comprobante buscar(Integer id) {
        return comprobanteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        comprobanteRepository.deleteById(id);
    }
}