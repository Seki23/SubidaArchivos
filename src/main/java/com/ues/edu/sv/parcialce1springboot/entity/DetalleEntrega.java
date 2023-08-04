package com.ues.edu.sv.parcialce1springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="detalle_entrega")
public class DetalleEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Productos productos;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_entrega", nullable = false)
    private EntregaVehiculo entrega;

}
