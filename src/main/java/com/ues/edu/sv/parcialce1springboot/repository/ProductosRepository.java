package com.ues.edu.sv.parcialce1springboot.repository;

import com.ues.edu.sv.parcialce1springboot.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos,Long> {
}
