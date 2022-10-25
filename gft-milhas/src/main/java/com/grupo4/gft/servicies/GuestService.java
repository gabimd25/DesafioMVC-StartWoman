package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.repositories.GuestRepository;

@Service
public class GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	
	public void saveGuest(Guest guest) {
		guestRepository.save(guest);
	}
	
	public List<Guest> listAllGuest(){
		return guestRepository.findAll();
	}
	
	public void deleteGuest(Long id) {
		guestRepository.deleteById(id);
	}

	public Guest getGuest(Long id) throws Exception {
		
     Optional<Guest> guest= guestRepository.findById(id);
		
		if(guest.isEmpty()) {
			throw new Exception("Participante não encontrado");
			
		}
		return guest.get();
	}
	
	

	public List<Guest> listGuest(String name, String fourletters) {

		if (name != null || fourletters != null)
			return listarGuestsForNameAndFourLetters(name);

		return listGuestWhole();
	}

	public List<Guest> listGuestWhole() {
		return guestRepository.findAll();
	}

	public List<Guest> listarGuestsForNameAndFourLetters(String name) {
		return guestRepository.findByNameContains(name);
	}

}
