package com.grupo4.gft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
		
	@NotEmpty(message="nome não pode ser vazio")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty(message="Nível não pode ser vazio")
	private String level;
	
	@NotEmpty(message="Quatro letras não pode ser vazio")
	@Size(min=4, max=4, message="Deve ter quatro letras")
	@Column(nullable = false, unique = true)
	private String fourLetters;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFourLetters() {
		return fourLetters;
	}

	public void setFourLetters(String fourLetters) {
		this.fourLetters = fourLetters;
	}
	
	
	

}
