package com.ues.edu.sv.parcialce1springboot.repository;

import com.ues.edu.sv.parcialce1springboot.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes,Long> {
}
