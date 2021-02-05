package com.app.my_pojos;

import java.sql.Date;

public class Voter {
	
	private int id;

	private String name, email, password;
	private boolean status;
	private String role;

	public Voter(String name, String email, String password, boolean status, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
	}
	
	public Voter(int id, String name, String email, String password, boolean status, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Voter [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", status="
				+ status + ", role=" + role + "]";
	}
	
}
