package com.grupo4.gft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Attendance {//Attendance

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAttendance;
	
	private String guestPresent;
	private String guestLate;
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID")
	private Event event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAttendance() {
		return dateAttendance;
	}

	public void setDateAttendance(Date dateAttendance) {
		this.dateAttendance = dateAttendance;
	}

	public String getGuestPresent() {
		return guestPresent;
	}

	public void setGuestPresent(String guestPresent) {
		this.guestPresent = guestPresent;
	}

	public String getGuestLate() {
		return guestLate;
	}

	public void setGuestLate(String guestLate) {
		this.guestLate = guestLate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
