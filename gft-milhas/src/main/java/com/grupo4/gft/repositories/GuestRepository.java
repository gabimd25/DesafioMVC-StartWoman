package com.grupo4.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo4.gft.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
