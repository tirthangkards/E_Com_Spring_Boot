package fun.bonkers.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fun.bonkers.model.Brand;
import fun.bonkers.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	BrandRepository brandRepository;
	
	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}
	
	public void addBrand(Brand brand) {
		brandRepository.save(brand);
	}

	public void deleteBrandByUUID(UUID id) {
		brandRepository.deleteById(id);
	}
	
	public Optional<Brand> findCategoryByUUID(UUID id) {
		return brandRepository.findById(id);
	}
}
