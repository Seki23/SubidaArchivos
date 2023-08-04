package com.ues.edu.sv.parcialce1springboot.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.ues.edu.sv.parcialce1springboot.dto.*;
import com.ues.edu.sv.parcialce1springboot.service.ProductosServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

;import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductosController {

   private final ProductosServices productosService;

   @PostMapping
    ResponseEntity<GenericResponse>guardarProductos(@RequestBody ProductosRequestDto productosRequestDto){
       return ResponseEntity.ok(productosService.guardarProducto(productosRequestDto));
   }
    @PostMapping("/insertar")
    ResponseEntity<GenericResponse> guardarProducto(@RequestParam("imagen") MultipartFile imagen, @RequestParam("json") String json) {
        try {
            ProductosRequestDto producto = new ObjectMapper().readValue(json, ProductosRequestDto.class);

            // Guardar la imagen en la carpeta del proyecto
            String nombreImagen = UUID.randomUUID().toString() + "-" + imagen.getOriginalFilename();
            String rutaCarpetaImagenes = "./uploads";
            Path rutaCompleta = Paths.get(rutaCarpetaImagenes, nombreImagen);
            Files.write(rutaCompleta, imagen.getBytes());

            // Guardar la URL de la imagen en el campo 'foto' del vehículo
            producto.setFoto(nombreImagen);

            // Guardar el vehículo en la base de datos
            return ResponseEntity.ok(productosService.guardarProducto(producto));
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();

            return null;
        }
    }

    @GetMapping("/image/{file}")
    public ResponseEntity<byte[]> getImage(@PathVariable("file") String filename) {
        byte[] image = new byte[0];
        String path = "./uploads";
        try {

            Path fileName = Paths.get(path, filename);
            image = Files.readAllBytes(fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

   @GetMapping
    ResponseEntity<List<ProductosResponseDto>> listarProductos(){
         return ResponseEntity.ok(productosService.listarProductos());
    }

    @GetMapping("/marcas")
    ResponseEntity<List<MarcasResponseDto>> listarMarcas(){
        return ResponseEntity.ok(productosService.listarMarcas());
    }
    @GetMapping("/tipos")
    ResponseEntity<List<TipoProductosResponseDto>> listarTipoProductos(){
        return ResponseEntity.ok(productosService.listarTipoProductos());
    }

    @PutMapping
    ResponseEntity<GenericResponse> actualizarProducto(@RequestBody ProductosRequestDto productosResponseDto){
       return ResponseEntity.ok(productosService.actualizarProducto(productosResponseDto));
    }

    @PutMapping("/actualizar")
    ResponseEntity<GenericResponse> actualizarProducto(@RequestParam(value = "imagen",required = false) MultipartFile imagen, @RequestParam("json") String json) {
        try {
            ProductosRequestDto producto = new ObjectMapper().readValue(json, ProductosRequestDto.class);

          if(imagen!=null && !imagen.isEmpty()){
              // Guardar la imagen en la carpeta del proyecto
              String nombreImagen = UUID.randomUUID().toString() + "-" + imagen.getOriginalFilename();
              String rutaCarpetaImagenes = "./uploads";
              Path rutaCompleta = Paths.get(rutaCarpetaImagenes, nombreImagen);
              Files.write(rutaCompleta, imagen.getBytes());

              // Guardar la URL de la imagen en el campo 'foto' del vehículo
              producto.setFoto(nombreImagen);
          }else{
                producto.setFoto("");
          }

            // Guardar el vehículo en la base de datos
            return ResponseEntity.ok(productosService.actualizarProducto(producto));
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();

            return null;
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<GenericResponse> eliminarProducto(@PathVariable Long id){
       return ResponseEntity.ok(productosService.eliminarProducto(id));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductosResponseDto> buscarProducto(@PathVariable Long id){
       return ResponseEntity.ok(productosService.buscarProducto(id));
    }

}
