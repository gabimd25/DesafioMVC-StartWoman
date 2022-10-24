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
import javax.validation.constraints.NotEmpty;

@Entity
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	private int amountPeople;
	
	@ManyToMany
	private List<Guest> participantes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID")
	private Event event;
	
	@OneToMany(mappedBy="group")
	private List<Attendance> attendance = new ArrayList<>();

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

	public int getAmountPeople() {
		return amountPeople;
	}

	public void setAmountPeople(int amountPeople) {
		this.amountPeople = amountPeople;
	}

	public List<Guest> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Guest> participantes) {
		this.participantes = participantes;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	
	

}
