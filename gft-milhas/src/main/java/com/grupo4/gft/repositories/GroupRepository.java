package com.grupo4.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo4.gft.entities.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

}
