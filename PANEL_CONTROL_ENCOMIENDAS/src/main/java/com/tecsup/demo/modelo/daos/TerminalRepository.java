package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {
}

