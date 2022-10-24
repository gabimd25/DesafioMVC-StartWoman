package com.grupo4.gft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attendance {//Attendance

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	private String fourLetters;
	private boolean attendance;
	private boolean late;
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private GroupEvent group;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFourLetters() {
		return fourLetters;
	}

	public void setFourLetters(String fourLetters) {
		this.fourLetters = fourLetters;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

	public GroupEvent getGroup() {
		return group;
	}

	public void setGroup(GroupEvent group) {
		this.group = group;
	}
}
