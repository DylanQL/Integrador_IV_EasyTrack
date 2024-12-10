package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}