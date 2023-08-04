package com.ues.edu.sv.parcialce1springboot.service;

import com.ues.edu.sv.parcialce1springboot.dto.*;
import com.ues.edu.sv.parcialce1springboot.entity.Marcas;
import com.ues.edu.sv.parcialce1springboot.entity.Productos;
import com.ues.edu.sv.parcialce1springboot.entity.TipoProductos;

import java.util.List;

public interface ProductosServices {

    GenericResponse guardarProducto(ProductosRequestDto productosRequestDto);


    List<ProductosResponseDto> listarProductos();

    List<MarcasResponseDto> listarMarcas();

    List<TipoProductosResponseDto> listarTipoProductos();
    GenericResponse actualizarProducto(ProductosRequestDto productosResponseDto);

    GenericResponse eliminarProducto(Long id);

    ProductosResponseDto buscarProducto(Long id);

}
