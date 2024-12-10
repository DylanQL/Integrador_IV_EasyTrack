package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.VehiculoRepository;
import com.tecsup.demo.modelo.entidades.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo buscar(Integer id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}