package com.gr.qrapi.core.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name= "alertProfile")
public class AlertProfile {

	private int id;
	private String name;
	private List<AlertProfileLocation> locations;
	private Account account;
	
	public AlertProfile() {
		
	}
	
	public AlertProfile(String name) {
		super();
		this.name = name;
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
	@Column(name= "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(targetEntity = AlertProfileLocation.class, mappedBy="alertProfile", fetch = FetchType.EAGER,  cascade=CascadeType.ALL, orphanRemoval=true)
	public List<AlertProfileLocation> getLocation() {
		return locations;
	}
	
	public void setLocation(List<AlertProfileLocation> locations) {
		this.locations = locations;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name = "account_Id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
