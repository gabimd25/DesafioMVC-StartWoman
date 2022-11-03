package com.grupo4.gft.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.User;
import com.grupo4.gft.repositories.UserRepository;
import com.grupo4.gft.util.CustomUserDetails;

@Service("userService")
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User user) throws Exception {

		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


		return userRepository.save(user);
	}

	public User getUser(Long id) throws Exception {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new Exception("Usuario não encontrado.");
		}
		return user.get();
	}


	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
		    throw new UsernameNotFoundException("Usuario não encontrado.");
		}
		return new CustomUserDetails(user);
				
	}
	
	 
}
