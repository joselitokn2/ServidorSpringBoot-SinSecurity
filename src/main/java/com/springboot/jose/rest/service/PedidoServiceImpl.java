package com.springboot.jose.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.springboot.jose.rest.exception.GlobalNotFoundException;
import com.springboot.jose.rest.model.Pedido;



@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	PedidoServiceImpl productoRepository;
	
	public Page<Pedido> findAll(Pageable pageable) {
		
		Page<Pedido> pedidoLista = productoRepository.findAll(pageable);
	
		if (pedidoLista.isEmpty()) {
			throw new GlobalNotFoundException("empty list");
		} else {
			return pedidoLista;
		}
	
	}

}
