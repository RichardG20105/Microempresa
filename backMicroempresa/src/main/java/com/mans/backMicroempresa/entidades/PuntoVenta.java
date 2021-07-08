package com.mans.backMicroempresa.entidades;

import javax.persistence.*;

@Entity
@Table(name ="puntoventa")

public class PuntoVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "idpventa")
	private Long idPventa;
	
	@Column(name = "nombrepventa")
	private String nombrePventa;
	
	@Column(name = "direccionpventa")
	private String direccionPventa;

	public PuntoVenta() {
		super();
	}

	public PuntoVenta(String nombrePventa, String direccionPventa) {
		super();
		this.nombrePventa = nombrePventa;
		this.direccionPventa = direccionPventa;
	}

	public Long getIdpventa() {
		return idPventa;
	}

	public void setIdpventa(Long idPventa) {
		this.idPventa = idPventa;
	}

	public String getNombrePventa() {
		return nombrePventa;
	}

	public void setNombrepventa(String nombrePventa) {
		this.nombrePventa = nombrePventa;
	}

	public String getDireccionPventa() {
		return direccionPventa;
	}

	public void setDireccionPventa(String direccionPventa) {
		this.direccionPventa = direccionPventa;
	}
}
