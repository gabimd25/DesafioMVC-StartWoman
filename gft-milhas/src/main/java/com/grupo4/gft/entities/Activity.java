package com.grupo4.gft.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotEmpty(message="nome não pode ser vazio")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deliveryDate;
	
	@ManyToMany
	private List<Guest> guestsFinished = new ArrayList<Guest>();
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<Guest> getGuestsFinished() {
		return guestsFinished;
	}

	public void setGuestsFinished(List<Guest> guestsFinished) {
		this.guestsFinished = guestsFinished;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", startDate=" + startDate + ", deliveryDate=" + deliveryDate
				+ ", guestsFinished=" + guestsFinished + ", event=" + event + "]";
	}


}