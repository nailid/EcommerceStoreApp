package com.example.demo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> addUser(@RequestBody Product product) throws Exception{
		if(product.getId() != null) {
			throw new Exception("Product already exists !!");
		}
		Product newProduct = this.productService.addProduct(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateUser(@RequestBody Product product) throws Exception{
		if(product.getId() == null) {
			throw new Exception("Product not exists !!");
		}
		Product updatedProduct = this.productService.addProduct(product);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProduct(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> addProduct(@PathVariable("id") Long id){
		Optional<Product> product = productService.findProduct(id);
		return product;
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		this.productService.delete(id);
	}
	
}
