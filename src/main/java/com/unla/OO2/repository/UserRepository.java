package com.unla.OO2.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.unla.OO2.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
}