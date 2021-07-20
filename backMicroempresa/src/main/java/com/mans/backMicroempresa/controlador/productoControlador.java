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
@CrossOrigin(origins = "*")
@RequestMapping("/Producto/")
public class productoControlador {
	@Autowired
	private productoServicios productoServicios;
	
	//Consultar todos los Productos
	@GetMapping("buscarProductos")
	public List<Producto> getProductos(){
		return this.productoServicios.findAll();
	}
	
	//Crear Producto
	@PostMapping("crearProducto")
	public Producto crearProducto(@RequestParam(name = "file", required = false)MultipartFile foto,@Valid @RequestBody Producto producto) throws ResourceNotFoundException {
		if(productoServicios.existsByNombreProducto(producto.getNombreProducto())) {
			throw new ResourceNotFoundException("Ya existe un Producto con ese Nombre");
		}
		if(!foto.isEmpty()) {
			String ruta = "C://Temp//uploads";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//"+foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				producto.setFotoProducto(foto.getOriginalFilename());
			}catch(Exception e) {
				//Errores de foto
			}
		}
		return this.productoServicios.save(producto);
	}
	
	//Modificar Producto
	@PutMapping("modificarProducto/{id}")
	public ResponseEntity<Producto> actualizarProducto(@RequestParam(name = "file", required = false)MultipartFile foto,@PathVariable(value = "id")Long idProducto,@Valid @RequestBody Producto productoDetalle) throws ResourceNotFoundException{
		Producto producto = productoServicios.findById(idProducto)
				.orElseThrow(()->new ResourceNotFoundException("No existe el producto con el Id: "+idProducto));

		producto.setNombreProducto(productoDetalle.getNombreProducto());
		if(producto.getFotoProducto() != null) {
			try {
				String ruta = "C://Temp//uploads";
				Path rutaAbsoluta = Paths.get(ruta + "//"+producto.getFotoProducto());
				Files.delete(rutaAbsoluta);
			}catch(Exception e){
				
			}
		}
		if(!foto.isEmpty()) {
			String ruta = "C://Temp//uploads";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//"+foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				producto.setFotoProducto(foto.getOriginalFilename());
			}catch(Exception e) {
				//Errores de foto
			}
		}
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
