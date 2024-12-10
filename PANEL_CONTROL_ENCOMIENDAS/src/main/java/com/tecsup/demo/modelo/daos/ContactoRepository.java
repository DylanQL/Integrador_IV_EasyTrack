package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}