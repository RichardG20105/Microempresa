package com.mans.backMicroempresa.entidades;

import javax.persistence.*;

@Entity
@Table(name = "Producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproducto")
	private long idproducto;
	
	@Column(name = "nombreproducto")
	private String nombreporducto;
	
	@Column(name = "fotoproducto")
	private String fotoproducto;
	
	public Producto() {
		super();
	}
	
	public Producto(String nombreporducto, String fotoproducto) {
		super();
		this.nombreporducto = nombreporducto;
		this.fotoproducto = fotoproducto;
	}
	
	public long getIdproducto() {
		return idproducto;
	}
	
	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}
	
	public String getNombreporducto() {
		return nombreporducto;
	}
	
	public void setNombreporducto(String nombreporducto) {
		this.nombreporducto = nombreporducto;
	}
	
	public String getFotoproducto() {
		return fotoproducto;
	}
	
	public void setFotoproducto(String fotoproducto) {
		this.fotoproducto = fotoproducto;
	}	
}
