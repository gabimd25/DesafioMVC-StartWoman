package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Activity;
import com.grupo4.gft.entities.Event;
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GuestService guestService;

	@Autowired
	private GuestService guestService;

	@Autowired
	private EventService eventService;

	public void saveGroupEvent(GroupEvent group) throws Exception {

		checkGuestUniqueInEvent(group);
		groupRepository.save(group);

	}

	public void deleteGroupEvent(Long id) {
		groupRepository.deleteById(id);
	}

	public GroupEvent getGroupEvent(Long id) throws Exception {

		Optional<GroupEvent> group = groupRepository.findById(id);

		if (group.isEmpty())
			throw new Exception("Grupo não encontrado");

		return group.get();
	}

	public List<GroupEvent> listAllGroupEvent() {
		return groupRepository.findAll();
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
			groupRepository.save(groupEvent);
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
			groupRepository.save(groupEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addGuest2(Long id, List<Guest> guestList) {
		GroupEvent groupEvent;
		try {

			groupEvent = getGroupEvent(id);
			groupEvent.addGuest(guestList);
			groupRepository.save(groupEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFinishedActivithScoreInGrupo(GroupEvent group) throws Exception {

		List<Guest> listGuestsGroup = group.getGuests();

		Long idEventGroup = group.getEvent().getId();

		// encontrar o evento atraves de id
		Event eventOfGroup = eventService.getEvent(idEventGroup);

		// evento encontra as atividades
		List<Activity> listActivityEvent = eventOfGroup.getActivities();

		//Boolean allGuestFinishedActivity = true;

		for (Activity activity : listActivityEvent) {
			for (Guest guest : activity.getGuestsFinished()) {
				for (Guest guestGroup : listGuestsGroup) {
					if (guest.getId() == guestGroup.getId()) {
						group.setPontuacao(group.getPontuacao() + 5);
					}
				}
			}

		}
	}

}
