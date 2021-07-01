package com.mans.backMicroempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mans.backMicroempresa.repositorio.productoServicios;

import com.mans.backMicroempresa.entidades.Producto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Producto/")
public class productoControlador {
	@Autowired
	private productoServicios productoServicios;
	
	@GetMapping("buscarProdcutos")
	public List<Producto> getProductos(){
		return this.productoServicios.findAll();
	}
	@PostMapping()
	public Producto postProducto() {
		return this.productoServicios.save(null);
	}
	@PutMapping()
	public ResponseEntity<Producto> actualizarProducto(){
		return ResponseEntity.ok(this.productoServicios.save(null));
	}
	@DeleteMapping()
	public ResponseEntity<?> deleteProducto(){
		return ResponseEntity.ok().build();
	}	
}
