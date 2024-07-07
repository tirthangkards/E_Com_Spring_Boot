package fun.bonkers.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.bonkers.model.Category;
import fun.bonkers.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public void deleteCategoryByUUID(UUID id) {
		categoryRepository.deleteById(id);
	}

	public Optional<Category> findCategoryByUUID(UUID id) {
		return categoryRepository.findById(id);
	}
}
