package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Activity;
import com.grupo4.gft.entities.Attendance;
import com.grupo4.gft.entities.Event;
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.repositories.ActivityRepository;
import com.grupo4.gft.repositories.AttendanceRepository;
import com.grupo4.gft.repositories.EventRepository;
import com.grupo4.gft.repositories.GroupEventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private GroupEventRepository groupEventRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private ActivityRepository activityRepository;

	public void saveEvent(Event event) throws Exception {
		boolean endStart = event.getEndDate().before(event.getStartDate());
		if (endStart)
			throw new Exception("Evento não pode ser salvo");

		eventRepository.save(event);

	}

	public void deleteEvent(Long id) {
		Event event;
		try {
			event = getEvent(id);
			if(event.getGroups()!=null)
				groupEventRepository.deleteAll(event.getGroups());
			if(event.getAttendances()!=null)
				attendanceRepository.deleteAll(event.getAttendances());
			if(event.getActivities()!=null)
				activityRepository.deleteAll(event.getActivities());
			eventRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	public void updateScore(Event event) {
		for (GroupEvent groupEvent : event.getGroups()) {
			groupEvent.setScorePresent((long) 0);
		}
		for (Attendance attendance : event.getAttendances()) {
			score(attendance);
		}
	}

	public void score(@Valid Attendance attendance) {
		String listPresent = "";
		String listLate = "";

		if (attendance.getGuestPresent() != null) {
			listPresent = attendance.getGuestPresent();
		}
		if (attendance.getGuestLate() != null) {
			listLate = attendance.getGuestLate();
		}

		for (GroupEvent groupEvent : attendance.getEvent().getGroups()) {
			int score = 0, cont = 0;
			for (Guest guest : groupEvent.getGuests()) {
				String fourL = guest.getFourLetters();
				if (listPresent.contains(fourL)) {
					score += 10;
					cont++;
				} else if (listLate.contains(fourL)) {
					score += 8;
					cont++;
				}
			}
			if (cont == groupEvent.getGuests().size())
				score += 5;
			groupEvent.updateScorePresent((long) score);
			groupEvent.setScoreTotal(groupEvent.getScoreActivity()+groupEvent.getScorePresent());
			groupEventRepository.save(groupEvent);
		}

	}

	public void addFinishedActivityScoreInGrupo(Event event) throws Exception {

		List<Activity> listActivityEvent = event.getActivities();

		for(Activity activity : listActivityEvent) {
			for(GroupEvent groupEvent : event.getGroups()) {
				int scoreActivity=0, cont=0;
				groupEvent.setScoreActivity((long) 0);
				for(Guest guest : groupEvent.getGuests()) {					
					if(activity.getGuestsFinished().contains(guest)) {
						scoreActivity+=5;
						cont++;
					}
				}
				if(cont == groupEvent.getGuests().size())
					scoreActivity+=3;
				groupEvent.updateScoreActivity((long) scoreActivity);
				groupEvent.setScoreTotal(groupEvent.getScoreActivity()+groupEvent.getScorePresent());
				groupEventRepository.save(groupEvent);
			}
		}
	}

}
