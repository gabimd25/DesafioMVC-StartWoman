package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Event;
import com.grupo4.gft.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public void saveEvent(Event event) {
		eventRepository.save(event);
	}
	
	public List<Event> listAllEvent(){
		return eventRepository.findAll();
	}
	
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

	public Event getEvent(Long id) throws Exception {
		
     Optional<Event> event= eventRepository.findById(id);
		
		if(event.isEmpty()) {
			throw new Exception("Evento não encontrado");
			
		}
		return event.get();
	}

}
