package com.ues.edu.sv.parcialce1springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name="precio", nullable = false)
    private double precio;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="presentacion", nullable = false, length = 50)
    private String presentacion;

  @Column(name="fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoProductos tipo;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marcas marca;

    @Column(name="foto", nullable = false)
    private String foto;

    @OneToMany(mappedBy = "productos")
    private Set<DetalleEntrega> detalle = new HashSet<>();




}
