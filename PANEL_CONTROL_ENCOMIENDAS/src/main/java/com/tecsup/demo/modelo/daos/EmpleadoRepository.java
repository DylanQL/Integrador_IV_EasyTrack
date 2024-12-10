package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}