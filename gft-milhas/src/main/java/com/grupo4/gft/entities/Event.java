package com.grupo4.gft.entities;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="event")
public class Event {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")	
	@NotEmpty(message="nome não pode ser vazio")
	private String name;
	
	
	@Column(name="startDate")	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(name="endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@OneToMany(mappedBy="event")
	private List<GroupEvent> groups = new ArrayList<>();
	
	@OneToMany(mappedBy="event")
	private List<Activity> activities = new ArrayList<>();

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<GroupEvent> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEvent> groups) {
		this.groups = groups;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public void addGroup(GroupEvent group) {
		this.groups.add(group);
	}

}
	
	
