package fun.bonkers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.bonkers.model.Gender;
import fun.bonkers.model.Product;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
	Optional<Gender> findGenderByName(String name);
}
