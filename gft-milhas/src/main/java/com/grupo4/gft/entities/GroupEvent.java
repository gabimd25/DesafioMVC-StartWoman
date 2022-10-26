package com.grupo4.gft.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GroupEvent {//Event
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany
	private List<Guest> guests = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID")
	private Event event;
	
	@OneToMany(mappedBy="group")
	private List<Attendance> presence = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void addGuest(Guest guest) {
		this.guests.add(guest);
	}
	
	public void addGuest(List<Guest> guest) {
		this.guests.addAll(guest);
	}
	
	public void removeGuest(Guest guest) {
		this.guests.remove(guest);
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public List<Attendance> getPresence() {
		return presence;
	}

	public void setPresence(List<Attendance> presence) {
		this.presence = presence;
	}
	
	
}
