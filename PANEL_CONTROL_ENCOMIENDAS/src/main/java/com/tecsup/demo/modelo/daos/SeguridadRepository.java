package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguridadRepository extends JpaRepository<Seguridad, Integer> {
}