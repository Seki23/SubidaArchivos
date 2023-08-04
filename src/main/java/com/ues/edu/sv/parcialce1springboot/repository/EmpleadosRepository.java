package com.ues.edu.sv.parcialce1springboot.repository;

import com.ues.edu.sv.parcialce1springboot.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleados,Long> {
}
