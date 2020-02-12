package com.springboot.jose.rest.dto;








import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class ProductoDTO {
	
	private long producto_id;
	
	private String nombre;
//	@JsonView(ProductoViews.DtoConPrecio.class)
	private float precio;
	private String descripcion;
//	@JsonView(ProductoViews.Dto.class)
	private int stock;

	private String imagen;
	private String nombreCategoria;

}
