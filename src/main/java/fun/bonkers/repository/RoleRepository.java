package fun.bonkers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.bonkers.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
