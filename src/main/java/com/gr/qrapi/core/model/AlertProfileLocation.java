package com.gr.qrapi.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "location")
public class AlertProfileLocation {

	private int id;
	private String city;
	private String country;
	private AlertProfile alertProfile;
	
	
	public AlertProfileLocation() {
		
	}
	
	public AlertProfileLocation(String city, String country, AlertProfile alertProfile) {
		super();
		this.city = city;
		this.country = country;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column (name="Id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column (name= "city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column (name= "country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name = "alertProfile_Id")
	public AlertProfile getAlertProfile() {
		return alertProfile;
	}
	
	public void setAlertProfile(AlertProfile alertProfile) {
		this.alertProfile = alertProfile;
	}
	
}
