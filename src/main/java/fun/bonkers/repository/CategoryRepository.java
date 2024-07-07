package fun.bonkers.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.bonkers.model.Category;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    void deleteById(UUID id);
    Optional<Category> findCategoryById(UUID id);
}
