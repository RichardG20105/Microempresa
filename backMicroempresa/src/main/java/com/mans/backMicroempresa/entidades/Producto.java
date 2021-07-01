package com.mans.backMicroempresa.entidades;

import javax.persistence.*;

@Entity
@Table(name = "Producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproducto")
	private Long idproducto;
	
	@Column(name = "nombreproducto")
	private String nombreproducto;
	
	@Column(name = "fotoproducto")
	private String fotoproducto;
	
	public Producto() {
		super();
	}
	
	public Producto(String nombreproducto, String fotoproducto) {
		super();
		this.nombreproducto = nombreproducto;
		this.fotoproducto = fotoproducto;
	}
	
	public long getIdproducto() {
		return idproducto;
	}
	
	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}
	
	public String getNombreproducto() {
		return nombreproducto;
	}
	
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	
	public String getFotoproducto() {
		return fotoproducto;
	}
	
	public void setFotoproducto(String fotoproducto) {
		this.fotoproducto = fotoproducto;
	}	
}
