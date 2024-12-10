package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {
}