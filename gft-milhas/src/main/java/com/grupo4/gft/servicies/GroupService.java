package com.grupo4.gft.servicies;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Event;
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.repositories.GroupEventRepository;

@Service
public class GroupService {

	@Autowired
	private GroupEventRepository groupEventRepository;
	
	@Autowired
	private GuestService guestService;


	public void saveGroupEvent(GroupEvent groupEvent) throws Exception {

		checkGuestUniqueInEvent(groupEvent);
		groupEventRepository.save(groupEvent);

	}

	public void deleteGroupEvent(Long id) {
		groupEventRepository.deleteById(id);
	}

	public GroupEvent getGroupEvent(Long id) throws Exception {

		Optional<GroupEvent> groupEvent = groupEventRepository.findById(id);

		if (groupEvent.isEmpty())
			throw new Exception("Grupo não encontrado");

		return groupEvent.get();
	}

	public List<GroupEvent> listAllGroupEvent() {
		return groupEventRepository.findAll();
	}
	
	public List<GroupEvent> listAllGroupEventByScore() {
		List<GroupEvent> groups = groupEventRepository.findAll();
			    
	    Comparator<GroupEvent> sortByScore = (t1, t2) -> t1.getScoreTotal().compareTo(t2.getScoreTotal());
	    List<GroupEvent> sortByScoreList = groups.stream().sorted(sortByScore).collect(Collectors.toList());
	    Comparator<GroupEvent> sortByScoreReverse = sortByScore.reversed();

		sortByScoreList = groups.stream().sorted(sortByScoreReverse).collect(Collectors.toList());
	
		return sortByScoreList;
	}

	public List<GroupEvent> findGroupEvent(String name) {

		return listAllGroupEvent();
	}

	public void removeGuest(Long id, Long idGuest) {
		GroupEvent groupEvent;
		Guest guest;
		try {
			groupEvent = getGroupEvent(id);
			guest = guestService.getGuest(idGuest);
			groupEvent.removeGuest(guest);
			groupEventRepository.save(groupEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkGuestUniqueInEvent(GroupEvent groupEvent) throws Exception {
		
		Event event = groupEvent.getEvent();
		List<GroupEvent>groups = event.getGroups();
		for (Guest newGuest : groupEvent.getGuests()) {
			for (GroupEvent group : groups) {
				if(group.getId()!=groupEvent.getId()) {
					for (Guest guest : group.getGuests()) {
						if (newGuest.getId() == guest.getId()) {
							throw new Exception("Participante ja existe em outro grupo");
						}
					}
				}
				
			}
		}

	}
}
