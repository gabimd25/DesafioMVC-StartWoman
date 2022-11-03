package com.grupo4.gft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.grupo4.gft.entities.User;
import com.grupo4.gft.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Test
	public void testCreateUser() {
		User user = new User();
		
		user.setEmail("ialm@gft.com");
		user.setFourletters("ialm");
		user.setName("Isabela lima");
		user.setPassword("isa123");
		
		User saveUser = userRepository.save(user);
		
		User existUser = entityManager.find(User.class, saveUser.getId());
		
		assertThat(existUser.getEmail().equalsIgnoreCase(user.getEmail()));
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "ialm@gft.com";
		
		User user = userRepository.findByEmail(email);
		
		assertThat(user).isNotNull();
		
	}

}
