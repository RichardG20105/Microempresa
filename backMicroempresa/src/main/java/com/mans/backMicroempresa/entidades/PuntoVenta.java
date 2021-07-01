package com.mans.backMicroempresa.entidades;

import javax.persistence.*;

@Entity
@Table(name ="puntoventa")

public class PuntoVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "idpventa")
	private Long idpventa;
	
	@Column(name = "nombrepventa")
	private String nombrepventa;
	
	@Column(name = "direccionpventa")
	private String direccionpventa;

	public PuntoVenta() {
		super();
	}

	public PuntoVenta(String nombrepventa, String direccionpventa) {
		super();
		this.nombrepventa = nombrepventa;
		this.direccionpventa = direccionpventa;
	}

	public Long getIdpventa() {
		return idpventa;
	}

	public void setIdpventa(Long idpventa) {
		this.idpventa = idpventa;
	}

	public String getNombrepventa() {
		return nombrepventa;
	}

	public void setNombrepventa(String nombrepventa) {
		this.nombrepventa = nombrepventa;
	}

	public String getDireccionpventa() {
		return direccionpventa;
	}

	public void setDireccionpventa(String direccionpventa) {
		this.direccionpventa = direccionpventa;
	}

	
}
