package com.course.login.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String name;
	private String lastName;
	private String role;
	private String country;
	
	public UsuarioDTO(String uid, String name, String lastName, String role, String country) {
		super();
		this.uid = uid;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.country = country;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
