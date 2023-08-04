package com.ues.edu.sv.parcialce1springboot.repository;


import com.ues.edu.sv.parcialce1springboot.dto.VentasTotalesResponseDto;
import com.ues.edu.sv.parcialce1springboot.entity.Zonas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zonas,Long> {

    @Query(value = "select z.nombre as nombreZona, sum(de.total)as totalVentas from entrega_vehiculo ev \n" +
            "inner join zonas z on ev.id_zona =z.id \n" +
            "inner join detalle_entrega de on de.id_entrega =ev.id \n" +
            "group by z.nombre \n", nativeQuery = true)
    List<VentasTotalesResponseDto> findVentasTotales();

}
