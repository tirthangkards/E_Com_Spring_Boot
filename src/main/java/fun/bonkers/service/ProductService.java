package fun.bonkers.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.bonkers.model.Category;
import fun.bonkers.model.Gender;
import fun.bonkers.model.Product;
import fun.bonkers.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProductByUUID(UUID id) {
		productRepository.deleteById(id);
	}

	public Optional<Product> findProductByUUID(UUID id) {
		return productRepository.findById(id);
	}
	
	public List<Product> findProductByCategoryAndGender(Optional<Category> category, Optional<Gender> gender) {
		return productRepository.findProductsByCategoryAndGender(category, gender);
	}
}
