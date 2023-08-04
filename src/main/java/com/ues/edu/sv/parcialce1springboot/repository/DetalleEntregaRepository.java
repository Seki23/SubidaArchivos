package com.ues.edu.sv.parcialce1springboot.repository;

import com.ues.edu.sv.parcialce1springboot.dto.VentasPorCamionyZonaResponseDto;
import com.ues.edu.sv.parcialce1springboot.entity.DetalleEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleEntregaRepository extends JpaRepository<DetalleEntrega,Long> {


    @Query(value = "select z.nombre as nombreZona,tv.descripcion as tipoVehiculo,count(v.id)as numVehiculos , ev.fecha_entrega as fechaEntrega, " +
            "SUM(case when tp.id=1 then de.total  else 0 end) as totalBebidasAlcoholicas, SUM(case when tp.id=2 then de.total else 0 end) as totalBebidasNoAlcoholicas," +
            " SUM(de.total) as totalVenta from zonas z inner join entrega_vehiculo ev ON z.id =ev.id_zona inner join detalle_entrega de on de.id_entrega =ev.id inner join" +
            " productos p on de.id_producto =p.id inner join vehiculos v on ev.id_vehiculo =v.id inner join tipo_vehiculo tv on v.id_tipo_vehiculo =tv.id " +
            "inner join tipo_productos tp on p.id_tipo =tp.id group by z.nombre ,tv.descripcion,ev.fecha_entrega order by total desc", nativeQuery = true)
    List<VentasPorCamionyZonaResponseDto> totalVentasPorZonasyCamion();

}
