package com.mans.backMicroempresa.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mans.backMicroempresa.repositorio.productoServicios;

import com.mans.backMicroempresa.entidades.Producto;
import com.mans.backMicroempresa.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800, allowCredentials = "false" )
@RequestMapping("/Producto/")
public class productoControlador {
	@Autowired
	private productoServicios productoServicios;
	
	//Consultar todos los Productos
	@GetMapping("buscarProductos")
	public List<Producto> getProductos(){
		return this.productoServicios.findAll();
	}
	
	@GetMapping("buscarProducto/{id}")
	public ResponseEntity<Producto> buscarProductoId(@PathVariable(name = "id")Long idProducto)throws ResourceNotFoundException{
		Producto producto = productoServicios.findById(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un Producto con el id: "+idProducto));
		return ResponseEntity.ok().body(producto);
	}
	
	//Crear Producto
	@PostMapping("crearProducto")
	public Producto crearProducto(@Valid @RequestBody Producto producto) throws ResourceNotFoundException {
		if(productoServicios.existsByNombreProducto(producto.getNombreProducto())) {
			throw new ResourceNotFoundException("Ya existe un Producto con ese Nombre");
		}
		return this.productoServicios.save(producto);
	}
	
	@PostMapping("subirImagen")
	public ResponseEntity<?> subirImagen(@RequestParam(name = "file") MultipartFile file)throws ResourceNotFoundException{
		if(!file.isEmpty()) {
			String ruta = "C://Temp//uploads";
			try {
				byte[] bytes = file.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//"+file.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
			}catch(Exception e) {
				//Errores de foto
			}
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("modificarImagen/{id}")
	public ResponseEntity<?> actualizarImagen(@PathVariable(value = "id")Long idProducto,@RequestParam(name = "file") MultipartFile file)throws ResourceNotFoundException{
		
		Producto producto = productoServicios.findById(idProducto)
				.orElseThrow(()->new ResourceNotFoundException("No existe el producto con el Id: "+idProducto));
		
		
		if(!file.isEmpty()) { //Si se recibe una imagen
			String ruta = "C://Temp//uploads";
			if(producto.getFotoProducto() != null) { //
				try {
					//Borra la imagen anterior
					Path rutaAbsoluta = Paths.get(ruta + "//"+producto.getFotoProducto());
					Files.delete(rutaAbsoluta);
				}catch(Exception e){
					
				}
			}
			
			try {
				//Guarda la nueva imagen
				byte[] bytes = file.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//"+file.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
			}catch (Exception e) {
				
			}
		}
		return ResponseEntity.ok().build();
		
	}
	
	//Modificar Producto
	@PutMapping("modificarProducto/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable(value = "id")Long idProducto,@Valid @RequestBody Producto productoDetalle) throws ResourceNotFoundException{
		Producto producto = productoServicios.findById(idProducto)
				.orElseThrow(()->new ResourceNotFoundException("No existe el producto con el Id: "+idProducto));
		
		producto.setNombreProducto(productoDetalle.getNombreProducto());
		producto.setFotoProducto(productoDetalle.getFotoProducto());
		
		return ResponseEntity.ok(this.productoServicios.save(producto));
	}
	
	//Borrar Producto
	@DeleteMapping("borrarProducto/{id}")
	public Map<String, Boolean> borrarProducto(@PathVariable(value = "id")Long idProducto)throws ResourceNotFoundException{
		Producto producto = productoServicios.findById(idProducto)
				.orElseThrow(()->new ResourceNotFoundException("No se encontro el Producto con el Id: "+idProducto));
		if(producto.getFotoProducto() != null) {
			try {
				String ruta = "C://Temp//uploads";
				Path rutaAbsoluta = Paths.get(ruta + "//"+producto.getFotoProducto());
				
				Files.delete(rutaAbsoluta);
			}catch(Exception e){
				
			}
		}
		this.productoServicios.delete(producto);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put(("Se elimino el Producto"), Boolean.TRUE);
		return response;
	}	
}
