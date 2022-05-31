package com.unla.OO2.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.unla.OO2.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	public Role findByName(String role);
}