package com.ues.edu.sv.parcialce1springboot.serviceImpl;

import com.ues.edu.sv.parcialce1springboot.dto.*;
import com.ues.edu.sv.parcialce1springboot.entity.Marcas;
import com.ues.edu.sv.parcialce1springboot.entity.Productos;
import com.ues.edu.sv.parcialce1springboot.entity.TipoProductos;
import com.ues.edu.sv.parcialce1springboot.repository.MarcasRepository;
import com.ues.edu.sv.parcialce1springboot.repository.ProductosRepository;
import com.ues.edu.sv.parcialce1springboot.repository.TipoProductosRepository;
import com.ues.edu.sv.parcialce1springboot.service.ProductosServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosServices {

    private final ProductosRepository productosRepository;

    private final MarcasRepository marcasRepository;

    private final TipoProductosRepository tiposRepository;

    private final HttpServletRequest request;

    @Override
    public GenericResponse guardarProducto(ProductosRequestDto request) {
        GenericResponse<Productos> rs = new GenericResponse<>();
        String resultado = "";
        try {
            Productos producto = new Productos();
            Optional<Marcas> marca = marcasRepository.findById(request.getMarca());
            Optional<TipoProductos> tipo = tiposRepository.findById(request.getTipo());

            if (marca.isPresent() && tipo.isPresent()) {
                producto.setNombre(request.getNombre());
                producto.setDescripcion(request.getDescripcion());
                producto.setPrecio(request.getPrecio());
                producto.setCantidad(request.getCantidad());
                producto.setPresentacion(request.getPresentacion());
                producto.setFechaVencimiento(convertirFecha(request.getFechaVencimiento()));
                producto.setMarca(marca.get());
                producto.setTipo(tipo.get());

                if (request.getFoto() != null) {
                    producto.setFoto(request.getFoto());
                } else {
                    producto.setFoto("");
                }


                productosRepository.save(producto);
                resultado = "Producto guardado";
            } else {
                resultado = "No existe marca o tipo de producto";
            }


        } catch (Exception e) {
            resultado = "Error al guardar producto";
        }
        rs.setCode(1);
        rs.setMessage(resultado);

        return rs;
    }

    public LocalDate convertirFecha(String fecha) {
        // Define el formato de la cadena de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convierte la cadena en LocalDate
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        // Ahora localDate contiene la fecha en formato LocalDate
       return localDate;

    }

    @Override
    public List<ProductosResponseDto> listarProductos() {
        List<ProductosResponseDto> productosResponseDtoList = new ArrayList<>();

        List<Productos> listpro = productosRepository.findAll();

        for (Productos productos : listpro) {
            ProductosResponseDto productosResponseDto = new ProductosResponseDto();
            productosResponseDto.setId(productos.getId());
            productosResponseDto.setNombre(productos.getNombre());
            productosResponseDto.setDescripcion(productos.getDescripcion());
            productosResponseDto.setPrecio(productos.getPrecio());
            productosResponseDto.setCantidad(productos.getCantidad());
            productosResponseDto.setPresentacion(productos.getPresentacion());
            productosResponseDto.setFechaVencimiento(productos.getFechaVencimiento());
            productosResponseDto.setMarca(new MarcasResponseDto(productos.getMarca().getId(), productos.getMarca().getDescripcion()));
            productosResponseDto.setTipo(new TipoProductosResponseDto(productos.getTipo().getId(), productos.getTipo().getDescripcion()));
            if (productos.getFoto() != null) {
                productosResponseDto.setFoto(generateUrlImg(productos.getFoto()));
            } else {
                productosResponseDto.setFoto("");
            }


            productosResponseDtoList.add(productosResponseDto);


        }


        return productosResponseDtoList;
    }

    public String generateUrlImg(String img) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return host + "/api/productos/image/" + img;
    }

    @Override
    public List<MarcasResponseDto> listarMarcas() {

        List<MarcasResponseDto> marcasResponseDtoList = new ArrayList<>();

        List<Marcas> listMa = marcasRepository.findAll();

        for (Marcas ma : listMa) {
            MarcasResponseDto marcasResponseDto = new MarcasResponseDto();
            marcasResponseDto.setId(ma.getId());
            marcasResponseDto.setDescripcion(ma.getDescripcion());

            marcasResponseDtoList.add(marcasResponseDto);


        }


        return marcasResponseDtoList;
    }

    @Override
    public List<TipoProductosResponseDto> listarTipoProductos() {
        List<TipoProductosResponseDto> tipoProductosResponseDtos = new ArrayList<>();

        List<TipoProductos> listype = tiposRepository.findAll();

        for (TipoProductos tp : listype) {
            TipoProductosResponseDto tipoProductosResponseDto = new TipoProductosResponseDto();
            tipoProductosResponseDto.setId(tp.getId());
            tipoProductosResponseDto.setDescripcion(tp.getDescripcion());

            tipoProductosResponseDtos.add(tipoProductosResponseDto);

        }
        return tipoProductosResponseDtos;
    }

    @Override
    public GenericResponse actualizarProducto(ProductosRequestDto request) {
        GenericResponse<Productos> rs = new GenericResponse<>();
        String resultado = "";
        try {
            Optional<Productos> producto = productosRepository.findById(request.getId());
            Optional<Marcas> marca = marcasRepository.findById(request.getMarca());
            Optional<TipoProductos> tipo = tiposRepository.findById(request.getTipo());

            if (marca.isPresent() && tipo.isPresent()) {
                if (producto.isEmpty()){
                    resultado = "No existe producto a actualizar";
                }else{
                    producto.get().setNombre(request.getNombre());
                    producto.get().setDescripcion(request.getDescripcion());
                    producto.get().setPrecio(request.getPrecio());
                    producto.get().setCantidad(request.getCantidad());
                    producto.get().setPresentacion(request.getPresentacion());
                    producto.get().setFechaVencimiento(convertirFecha(request.getFechaVencimiento()));
                    producto.get().setMarca(marca.get());
                    producto.get().setTipo(tipo.get());

                    if (request.getFoto() != null && !request.getFoto().isEmpty()) {
                        producto.get().setFoto(request.getFoto());
                    }

                    productosRepository.save(producto.get());
                    resultado = "Producto Actualizado";
                }

            } else {
                resultado = "No existe marca o tipo de producto a actualizar";
            }
        } catch (Exception e) {
            resultado = "Error al Actualizar producto";
        }
        rs.setCode(1);
        rs.setMessage(resultado);
        return rs;
    }

    @Override
    public GenericResponse eliminarProducto(Long id) {
        GenericResponse<Productos> rs = new GenericResponse<>();
        String resultado = "";
        try {

            Optional<Productos> productos = productosRepository.findById(id);
            if (!productos.isPresent()) {
                resultado = "No existe producto";
            } else {
                productosRepository.delete(productos.get());
                resultado = "Producto elieminado";
            }


        } catch (Exception e) {
            resultado = "Error al eliminar producto";
            throw new RuntimeException();
        }
        rs.setCode(1);
        rs.setMessage(resultado);
        return rs;
    }

    @Override
    public ProductosResponseDto buscarProducto(Long id) {
        Optional<Productos> productos = productosRepository.findById(id);
        if (productos.isPresent()) {
            ProductosResponseDto productosResponseDto = new ProductosResponseDto();
            productosResponseDto.setId(productos.get().getId());
            productosResponseDto.setNombre(productos.get().getNombre());
            productosResponseDto.setDescripcion(productos.get().getDescripcion());
            productosResponseDto.setPrecio(productos.get().getPrecio());
            productosResponseDto.setCantidad(productos.get().getCantidad());
            productosResponseDto.setPresentacion(productos.get().getPresentacion());
            productosResponseDto.setFechaVencimiento(productos.get().getFechaVencimiento());
            return productosResponseDto;
        }
        return null;
    }
}
