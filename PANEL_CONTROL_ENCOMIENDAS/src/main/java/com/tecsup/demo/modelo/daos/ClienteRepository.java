package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}