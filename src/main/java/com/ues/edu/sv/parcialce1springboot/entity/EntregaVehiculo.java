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
@Table(name="entrega_vehiculo")
public class EntregaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculos vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    private Zonas zonas;

    @Column(name="fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleEntrega> detalles = new HashSet<>();










}
