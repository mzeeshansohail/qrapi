package com.gr.qrapi.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	private int id;
	private String lineOne;
	private String lineTwo;
	private String city;
	private String state;
	private String country;

	public Address() {
	}

	public Address(String lineOne, String lineTwo, String city, String state, String country) {
		super();
		this.lineOne = lineOne;
		this.lineTwo = lineTwo;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	@Column (name= "line_one")
	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}
	
	@Column (name= "line_two")
	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	@Column (name= "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column (name= "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column (name= "country")
	public String getCountry() {
		return country;
		
	}

	public void setCountry(String country) {
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
	
}
