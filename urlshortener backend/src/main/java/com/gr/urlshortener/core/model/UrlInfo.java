package com.gr.urlshortener.core.model;

import java.io.Serializable;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "urlinfo")
public class UrlInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private String originalUrl;
	private String shortUrl;
	private Date createdOn;
	private int totalClicksCount;
	private Date expiryDate;
	private List<Clicks> clicks;
	
	public UrlInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "original_Url")
	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	@Column(name = "short_Url")
	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="CET") 
	@Column(name = "created_On", columnDefinition = "java.util.Date")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "total_Clicks_Count")
	public int getTotalClicksCount() {
		return totalClicksCount;
	}

	public void setTotalClicksCount(int totalClicksCount) {
		this.totalClicksCount = totalClicksCount;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="CET") 
	@Column(name= "expiry_Date")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JsonBackReference
	@OneToMany(mappedBy="urlinfo", fetch = FetchType.LAZY,  cascade=CascadeType.ALL)
	public List<Clicks> getClicks() {
		return clicks;
	}

	public void setClicks(List<Clicks> clicks) {
		this.clicks = clicks;
	}
	
	
}
