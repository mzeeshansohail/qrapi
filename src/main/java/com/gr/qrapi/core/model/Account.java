package com.gr.qrapi.core.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "account")
public class Account {
	private int id;
	private String name;
	private String email;
	private String city;
	private List<Contact> contacts;
	private List<AlertProfile> alertProfiles;
	
	public Account() {
	}

	public Account(String name, String email, String city) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="Id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	@Column (name="Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column (name="Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name= "City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
    @OneToMany(targetEntity = Contact.class, mappedBy="account", fetch = FetchType.EAGER,  cascade=CascadeType.ALL, orphanRemoval=true)
	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
    @OneToMany(targetEntity = AlertProfile.class, mappedBy="account", fetch = FetchType.EAGER,  cascade=CascadeType.ALL, orphanRemoval=true)
	public List<AlertProfile> getAlertProfiles() {
		return alertProfiles;
	}

	public void setAlertProfiles(List<AlertProfile> alertProfiles) {
		this.alertProfiles = alertProfiles;
	}
	
	
}