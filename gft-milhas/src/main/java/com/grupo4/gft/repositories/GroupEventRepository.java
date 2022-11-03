package com.grupo4.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo4.gft.entities.GroupEvent;

@Repository
public interface GroupEventRepository extends JpaRepository<GroupEvent, Long> {

	public GroupEvent findByName(String name);
}
