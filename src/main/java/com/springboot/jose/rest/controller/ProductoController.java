
package com.springboot.jose.rest.controller;



import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import com.springboot.jose.rest.dto.CreateProductoDTO;
import com.springboot.jose.rest.dto.ProductoDTO;
import com.springboot.jose.rest.dto.converter.ProductoDTOConverter;
import com.springboot.jose.rest.exception.GlobalNotFoundException;
import com.springboot.jose.rest.exception.GlobalSearchException;
import com.springboot.jose.rest.model.Producto;
import com.springboot.jose.rest.repository.CategoriaRepository;
import com.springboot.jose.rest.repository.ProductoRepository;
import com.springboot.jose.rest.service.ProductoService;
import com.springboot.jose.rest.upload.StorageService;
import com.springboot.jose.rest.util.PaginationLinksUtils;

import lombok.RequiredArgsConstructor;

/**
 * @author duw203
 *
 */

@RestController
@RequiredArgsConstructor
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoDTOConverter productoDTOconverter;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	StorageService storageService;

	@Autowired
	PaginationLinksUtils paginationLinksUtils;

	/*BUSCAR PRODUCTO POR ID*/
	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getItem(@PathVariable Long id) {
		if (id <= 0) {
			throw new GlobalNotFoundException("Invalid id");
		}
		Producto producto = productoService.getProducto(id);
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	/*BUSCAR POR NOMBRE PARAM: nombre + size*/
	@GetMapping(value = "/producto", params = "nombre")
	public ResponseEntity<?> buscarProductosPorNombre(@RequestParam("nombre") String nombre, Pageable pageable,
			HttpServletRequest request) {

		Page<ProductoDTO> allProductos = productoService.findByNombre(nombre, pageable);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(allProductos, uriBuilder))
				.body(allProductos);

	}

	
//	@GetMapping("/producto/all")
//	public ResponseEntity<List<ProductoDTO>> allProductos() {
//		List<ProductoDTO> producto = productoService.allProductos();
//		return new ResponseEntity<>(producto, HttpStatus.OK);
//	}
	
	/*BUSCAR TODOS PAGINADOS */
	
	@GetMapping("/producto/all")
	public ResponseEntity<Page<ProductoDTO>> allProductos(@PageableDefault(size = 5, page = 0) Pageable pageable,
			HttpServletRequest request) {
		Page<ProductoDTO> allproductos = productoService.allProductos(pageable);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(allproductos, uriBuilder))
				.body(allproductos);
	}
	
	/*Insertamos productos directamente sobre el prouducto- MALA IDEA*/
	
	@PostMapping("/producto/add")
	public ResponseEntity<CreateProductoDTO> addProducto(@RequestBody CreateProductoDTO createProductoDTO) {
		productoService.addProducto(createProductoDTO);
		return new ResponseEntity<>(createProductoDTO, HttpStatus.CREATED);
	}

	/**
	 * Insertamos un nuevo producto con imagen
	 * 
	 * @param nuevo + file
	 * @return 201 y el producto insertado
	 */
	@PostMapping(value = "/producto", consumes= MediaType.MULTIPART_FORM_DATA_VALUE) //Aunque no es obligatorio, podemos indicar que se consume multipart/form-data
	public ResponseEntity<?> nuevoProducto(@RequestPart("nuevo") CreateProductoDTO nuevo, 
			@RequestPart("file") MultipartFile file) {
	/*	
		// Almacenamos el fichero y obtenemos su URL
		String urlImagen = null;
		
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			urlImagen = MvcUriComponentsBuilder
							// El segundo argumento es necesario solo cuando queremos obtener la imagen
							// En este caso tan solo necesitamos obtener la URL
							.fromMethodName(FicherosController.class, "serveFile", imagen, null)  
							.build().toUriString();
		}
		
		// Construimos nuestro nuevo Producto a partir del DTO
		//Seria bueno que nos devuelva el producto en si cuando lo creamos
	
		productoService.addProducto(createProductoDTO, urlImagen);
		return new ResponseEntity<>(createProductoDTO , HttpStatus.CREATED);
		*/
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.addProducto(nuevo, file));
	}
	/*OTRA FORMA SERIA ESTA
	 * 
	 * 
	
	@PostMapping(value = "/producto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Aunque no es obligatorio,
																						// podemos indicar que se
																						// consume multipart/form-data
	public ResponseEntity<Producto> nuevoProducto(@RequestPart("nuevo") CreateProductoDTO nuevo,
			@RequestPart("file") MultipartFile file) {

		return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.nuevoProducto(nuevo, file));
	}

	 * */



	@PutMapping("/producto/update/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
		if (producto != null) {
			productoService.updateProducto(id, producto);
		}
		return new ResponseEntity<>(producto, HttpStatus.OK);
	}
	/**
	 * Borra un producto del catálogo en base a su id
	 * 
	 * @param id
	 * @return Código 204 sin contenido
	 */

	@DeleteMapping("/producto/delete/{id}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable Long id) {
		Producto producto = productoService.getProducto(id);
		productoService.deleteProducto(id);
		return new ResponseEntity<>(producto, HttpStatus.NO_CONTENT);
	}

}


/**
 * Método que nos permite buscar sobre una lista de productos
 * 
 * @param txt      Fragmento del nombre del producto
 * @param precio   Precio tope
 * @param pageable
 * @param request
 * @return 200 OK. Si hay productos (o si hay productos que cumplan los
 *         parámetros de búsqueda).
 */

//@JsonView(ProductoViews.DtoConPrecio.class)
//@GetMapping(value = "/producto")
//public ResponseEntity<?> buscarProductosPorVarios(@RequestParam("nombre") Optional<String> txt,
//		@RequestParam("precio") Optional<Float> precio, Pageable pageable, HttpServletRequest request) {
//
//	Page<Producto> result = productoServicio.findByArgs(txt, precio, pageable);
//
//	if (result.isEmpty()) {
//		throw new SearchProductoNoResultException();
//	} else {
//
//		Page<ProductoDTO> dtoList = result.map(productoDTOConverter::convertToDto);
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
//
//		return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
//				.body(dtoList);
//
//	}
//
//}

