package com.grupo4.gft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.grupo4.gft.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	List<Activity> findByNameContains(String name);
}
