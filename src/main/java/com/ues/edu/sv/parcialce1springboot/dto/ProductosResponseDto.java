package com.ues.edu.sv.parcialce1springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ProductosResponseDto {

    private Long id;

    private String nombre;

    private String descripcion;

    private double precio;

    private int cantidad;

    private String presentacion;

    private LocalDate fechaVencimiento;

    private TipoProductosResponseDto tipo;

    private MarcasResponseDto marca;

    private String foto;
}
