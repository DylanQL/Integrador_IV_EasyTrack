package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.ClienteRepository;
import com.tecsup.demo.modelo.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }
}