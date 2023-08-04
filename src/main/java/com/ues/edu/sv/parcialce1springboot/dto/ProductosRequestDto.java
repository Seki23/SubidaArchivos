package com.ues.edu.sv.parcialce1springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductosRequestDto {

    private Long  id;

    private String nombre;

    private String descripcion;

    private double precio;

    private int cantidad;

    private String presentacion;

    private String fechaVencimiento;

    private Long tipo;

    private Long marca;

    private String foto;

}
