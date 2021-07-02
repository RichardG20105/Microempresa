package com.mans.backMicroempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mans.backMicroempresa.repositorio.puntoVentaServicios;

import com.mans.backMicroempresa.entidades.PuntoVenta;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/PuntoVenta/")

public class puntoVentaControlador {
	@Autowired
	private puntoVentaServicios puntoVentaServicios;
	
	@GetMapping("buscarPuntoVenta")
	public List<PuntoVenta> buscarPuntoVenta(){
		return this.puntoVentaServicios.findAll();
	}
	
	@PostMapping("crearPuntoVenta")
	public PuntoVenta crearPuntoVenta() {
		return this.puntoVentaServicios.save(null);
	}
	
	@PutMapping("actualizarPuntoVenta")
	public ResponseEntity<PuntoVenta> actualizarPuntoVenta(){
		return ResponseEntity.ok(this.puntoVentaServicios.save(null));
	}
	
	@DeleteMapping("borrarPuntoVenta")
	public ResponseEntity<?> borrarPuntoVenta(){
		return ResponseEntity.ok().build();
	}
	
}