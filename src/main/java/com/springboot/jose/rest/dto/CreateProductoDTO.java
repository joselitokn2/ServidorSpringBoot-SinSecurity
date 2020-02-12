package com.springboot.jose.rest.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class CreateProductoDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long producto_id;
	
	private String nombre;
	
	private float precio;
	
	private String descripcion;
	
	private int stock;
	
	private String imagen;
	
	private long categoria_id;
	
	
}
