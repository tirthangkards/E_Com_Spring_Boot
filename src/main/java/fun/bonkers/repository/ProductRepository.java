package fun.bonkers.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.bonkers.model.Category;
import fun.bonkers.model.Gender;
import fun.bonkers.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
	void deleteById(UUID id);
	Optional<Product> findProductById(UUID id);
	List<Product> findProductsByCategoryAndGender(Optional<Category> category, Optional<Gender> gender);
}
