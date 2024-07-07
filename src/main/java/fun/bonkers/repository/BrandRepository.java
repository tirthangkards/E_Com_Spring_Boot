package fun.bonkers.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.bonkers.model.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
	void deleteById(UUID id);
	Optional<Brand> findCategoryById(UUID id);
}
