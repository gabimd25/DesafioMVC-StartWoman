package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Activity;
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

	
	@Autowired
	private EventService eventService;

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

		for (Guest newGuest : groupEvent.getGuests()) {
			for (GroupEvent group : event.getGroups()) {
				for (Guest guest : group.getGuests()) {
					if (newGuest.getId() == guest.getId()) {
						throw new Exception("Participante ja existe em outro grupo");

					}
				}
			}
		}

	}

	public void addGuest(Long id, Long idGuest) {
		GroupEvent groupEvent;
		Guest guest;

		try {
			groupEvent = getGroupEvent(id);
			guest = guestService.getGuest(idGuest);

			groupEvent.addGuest(guest);
			groupEventRepository.save(groupEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addGuest2(Long id, List<Guest> guestList) {
		GroupEvent groupEvent;
		try {

			groupEvent = getGroupEvent(id);
			groupEvent.addGuest(guestList);
			groupEventRepository.save(groupEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFinishedActivithScoreInGrupo(GroupEvent groupEvent) throws Exception {

		List<Guest> listGuestsGroup = groupEvent.getGuests();

		Long idEventGroup = groupEvent.getEvent().getId();

		// encontrar o evento atraves de id
		Event eventOfGroup = eventService.getEvent(idEventGroup);

		// evento encontra as atividades
		List<Activity> listActivityEvent = eventOfGroup.getActivities();

		//Boolean allGuestFinishedActivity = true;

		for (Activity activity : listActivityEvent) {
			for (Guest guest : activity.getGuestsFinished()) {
				for (Guest guestGroup : listGuestsGroup) {
					if (guest.getId() == guestGroup.getId()) {
						groupEvent.setScoreActivity((long) groupEvent.getScoreActivity() + 5);
					}
				}
			}

		}
		
	}
	

}
