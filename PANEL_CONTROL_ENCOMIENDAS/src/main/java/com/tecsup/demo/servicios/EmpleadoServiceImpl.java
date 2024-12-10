package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.EmpleadoRepository;
import com.tecsup.demo.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado buscar(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }
}