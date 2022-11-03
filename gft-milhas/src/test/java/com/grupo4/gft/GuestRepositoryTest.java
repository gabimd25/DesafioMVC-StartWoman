package com.grupo4.gft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.repositories.GuestRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class GuestRepositoryTest {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Test
	public void testCreateGuest() {
		Guest guest = new Guest();
		
		guest.setEmail("ialm@gft.com");
		guest.setFourLetters("ialm");
		guest.setName("Isabela lima");
		guest.setLevel("l2");
		
		Guest saveGuest = guestRepository.save(guest);
		
		Guest existGuest = entityManager.find(Guest.class, saveGuest.getId());
		
		assertThat(existGuest.getEmail().equalsIgnoreCase(guest.getEmail()));
	}
	
	@Test
	public void testFindGuestByName() {
		String name = "Isabela lima";
		
		Guest guest = (Guest) guestRepository.findByNameContains(name);
		
		assertThat(guest).isNotNull();
		
	}


}
