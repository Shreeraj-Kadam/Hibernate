package edu.ty.foodapplicationproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_branch")
	@SequenceGenerator(name = "seq_gen_branch", initialValue = 501, allocationSize = 1, sequenceName = "seq_branch")
	private long id;
	private String name;
	private String address;
	private long phoneNumber;
	private String email;
	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
