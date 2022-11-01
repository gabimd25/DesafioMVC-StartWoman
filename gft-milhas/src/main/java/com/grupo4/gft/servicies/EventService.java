package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Event;
import com.grupo4.gft.repositories.EventRepository;
import com.grupo4.gft.repositories.GroupRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private GroupRepository groupEventRepository;

	public void saveEvent(Event event) throws Exception {
		boolean endStart= event.getEndDate().before(event.getStartDate());
		if(endStart) 
			throw new Exception("Evento não pode ser salvo");
			
		eventRepository.save(event);	
				
	}

	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

	public Event getEvent(Long id) throws Exception {

		Optional<Event> event = eventRepository.findById(id);

		if (event.isEmpty()) 
			throw new Exception("Evento não encontrado");

		return event.get();
	}

	public List<Event> listAllEvent() {
		return eventRepository.findAll();
	}

	public List<Event> findEventByName(String name) {
		return eventRepository.findByNameContains(name);

	}

	public List<Event> findEvent(String name) {

		if (name != null)
			return findEventByName(name);

		return listAllEvent();
	}


}
