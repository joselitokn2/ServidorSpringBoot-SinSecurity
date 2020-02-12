package com.springboot.jose.rest.exception;

public class PedidoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PedidoNotFoundException() {
		super("No se han encontrado pedidos");
	}

	public PedidoNotFoundException(Long id) {
		super("No se ha encontrado ningún pedido con el ID " + id);
	}
	

}
