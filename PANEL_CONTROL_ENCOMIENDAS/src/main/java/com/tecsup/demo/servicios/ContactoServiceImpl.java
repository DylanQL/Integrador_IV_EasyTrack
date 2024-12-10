package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.ContactoRepository;
import com.tecsup.demo.modelo.entidades.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Contacto> listar() {
        return contactoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Contacto contacto) {
        contactoRepository.save(contacto);
    }

    @Override
    @Transactional(readOnly = true)
    public Contacto buscar(Integer id) {
        return contactoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        contactoRepository.deleteById(id);
    }
}