package fun.bonkers.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.bonkers.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	User findByEmail(String email);
}
