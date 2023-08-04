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
@Table(name="empleados")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name="dui", nullable = false, length = 10)
    private String dui;

    @Column(name="telefono", nullable = false, length = 9)
    private String telefono;

    @Column(name="direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name="cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name="salario", nullable = false, length = 50)
    private double salario;

    @ManyToMany
    @JoinTable( name = "vehiculos_empleados",
         joinColumns = @JoinColumn(name = "id_empleado"),
         inverseJoinColumns = @JoinColumn(name = "id_vehiculo"))
    private Set<Vehiculos> vehiculosSet = new HashSet<>();

}
