package com.springboot.jose.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.jose.rest.dto.CreateProductoDTO;
import com.springboot.jose.rest.dto.ProductoDTO;
import com.springboot.jose.rest.model.Producto;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	/**
	 * Opción 1 con ModelMapper
	 * @param producto
	 * @return
	 */
	
	/*Transforma un producto en un producto nuevo*/
	public ProductoDTO convertToDto(Producto producto) {
		return modelMapper.map(producto, ProductoDTO.class);
	}

	
	public Producto createdToDTO(CreateProductoDTO createProductoDTO) {
		
		return modelMapper.map(createProductoDTO, Producto.class);
	}
	
	/**
	 * Opción 2 con Builder de Lombok
	 * @param producto
	 * @return
	 */
	public ProductoDTO convertProdutoToProductoDto(Producto producto) {
		return ProductoDTO.builder()
				.nombre(producto.getNombre())
				.imagen(producto.getImagen())
				.nombreCategoria(producto.getCategoria().getNombre())
				.producto_id(producto.getProducto_id())
				.build();
	}
	

}
