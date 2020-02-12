package com.springboot.jose.rest.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5807618819942378976L;

	/**
	 * 
	 */

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoria_id;	
	
	private String nombre;

	
//	@Version
//	private long version;
	
}
