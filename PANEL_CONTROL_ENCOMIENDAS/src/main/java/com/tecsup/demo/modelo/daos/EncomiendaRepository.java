package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomiendaRepository extends JpaRepository<Encomienda, Integer> {
}