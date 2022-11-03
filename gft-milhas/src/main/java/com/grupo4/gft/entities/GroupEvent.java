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

@Entity
public class GroupEvent {//Event
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany
	private List<Guest> guests = new ArrayList<>();
	
	private Long scorePresent=(long) 0;
	private Long scoreActivity=(long) 0;
	private Long scoreTotal=(long) 0;

	@ManyToOne
	@JoinColumn(name="EVENT_ID")
	private Event event; 
	
	
	
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

	public void updateScorePresent(Long scorePresent) {
		this.scorePresent += scorePresent;
	}
	
	public Long getScorePresent() {
		return scorePresent;
	}

	public void setScorePresent(Long scorePresent) {
		this.scorePresent = scorePresent;
	}

	public void updateScoreActivity(Long scoreActivity) {
		this.scoreActivity += scoreActivity;
	}
	
	public Long getScoreActivity() {
		return scoreActivity;
	}

	public void setScoreActivity(Long scoreActivity) {
		this.scoreActivity = scoreActivity;
	}	

	public Long getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Long scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
	
	
	
}
