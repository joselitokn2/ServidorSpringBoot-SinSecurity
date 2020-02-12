package com.springboot.jose.rest.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Producto implements Serializable {
	
	
	private static final long serialVersionUID = 7897940257027322024L;
	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generador de valor
	private long producto_id;

	private String nombre; 
	private float precio;
	private String descripcion;
	private int stock;
	private String imagen;
	private boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	

//	@Version
//	private long version;



	
	
	
}
