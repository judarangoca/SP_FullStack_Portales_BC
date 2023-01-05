package com.sophos.MiniBankV1.security.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;


@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(length = 50, nullable = false, unique=true)
	private String username;
	
	@Column(length = 255, nullable = false, unique=true)
	private String password;
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 50)
	private String surname;
	
	@Column(name="mail", nullable=false, length=50, unique=true)
	@Email
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER) //Fetch Eager = Busqueda ansiosa
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_Id"),inverseJoinColumns = @JoinColumn(name = "role_Id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		super();
	}
	
	public User(String username, String password, String name, String surname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

	
}
