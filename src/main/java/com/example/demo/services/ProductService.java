package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public Product addProduct(Product product) {
		product = productRepo.save(product);
		return product;
	}
	public Optional<Product> findProduct(Long productId){
		Optional<Product> product = this.productRepo.findById(productId);
		return product;
	}
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public void delete(Long productId) {
		Optional<Product> product = this.productRepo.findById(productId);
		this.productRepo.deleteById(productId);
	}

}
