package com.assigment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assigment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	User findByEmailIgnoreCase(String email);

	@Query("SELECT u FROM User u where u.id=:userId")
	Optional<User> findById(@Param("userId") Long userId);

	

}
