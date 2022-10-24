package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Event;
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	public void saveGroupEvent(GroupEvent group) {
		groupRepository.save(group);
	}

	public void deleteGroupEvent(Long id) {
		groupRepository.deleteById(id);
	}

	public GroupEvent getGroupEvent(Long id) throws Exception {

		Optional<GroupEvent> group = groupRepository.findById(id);

		if (group.isEmpty()) 
			throw new Exception("Evento não encontrado");

		
		return group.get();
	}

	public List<GroupEvent> listAllGroupEvent() {
		return groupRepository.findAll();
	}
	
	public List<GroupEvent> findGroupEvent(String name) {

		//if (name != null)
			//return findEventByName(name);

		return listAllGroupEvent();
	}
}
