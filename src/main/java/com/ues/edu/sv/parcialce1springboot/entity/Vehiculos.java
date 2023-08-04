package com.ues.edu.sv.parcialce1springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehiculos")
public class Vehiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="placa", nullable = false, length = 7)
    private String placa;

    @Column(name="marca", nullable = false, length = 50)
    private String marca;

    @Column(name="modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name="color", nullable = false, length = 50)
    private String color;

    @ManyToOne
    @JoinColumn(name="id_tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;

   @ManyToMany
    @JoinTable( name = "vehiculos_empleados",
         joinColumns = @JoinColumn(name = "id_vehiculo"),
         inverseJoinColumns = @JoinColumn(name = "id_empleado"))
    private Set<Empleados> empleadosSet = new HashSet<>();

}
