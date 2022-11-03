package com.grupo4.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo4.gft.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT iUser FROM User iUser WHERE iUser.email = :email")
	public User findByEmail(String email);
	
	
	@Query("SELECT iUser FROM User iUser WHERE iUser.email = :email AND iUser.password = :password")
	public User findByEmailAndfindByPassword(String email, String password);
	
	@Query("SELECT iUser FROM User iUser WHERE iUser.name = :name")
	public User findByNameContains(String name);
	

}
