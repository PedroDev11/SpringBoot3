package com.cursos.spring_security_course.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cursos.spring_security_course.entity.Product;
import com.cursos.spring_security_course.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${security.jwt.baseUrl}")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> findAll() {
		
		List<Product> products = productRepository.findAll();
		
		if(!products.isEmpty()) {
			return ResponseEntity.ok(products);
		}
		
		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
	@PostMapping("/addProduct")
	public ResponseEntity<Product> createOne(@RequestBody @Valid Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				productRepository.save(product)
		);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handlerGenericException(Exception exception, HttpServletRequest request) {
		Map<String, String> apiError = new HashMap<>();
		apiError.put("message", exception.getLocalizedMessage());
		apiError.put("timestamp", new Date().toString());
		apiError.put("url", request.getRequestURL().toString());
		apiError.put("http-method", request.getMethod());

		// default
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		// Access Denied
		if (exception instanceof AccessDeniedException) {
			status = HttpStatus.FORBIDDEN;
		}

		return ResponseEntity.status(status).body(apiError);

	}
}
