package com.gr.urlshortener.core.model;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "clicks")
public class Clicks {

	private int id;
	private int click;
	private String platform;
	private String browser;
	private Date dateClicked;
	private UrlInfo urlInfo;

	public Clicks() {
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

	@Column(name = "clicks")
	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	@Column(name = "platform")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Column(name = "browser")
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Column(name = "clickDate")
	public Date getDateClicked() {
		return dateClicked;
	}

	public void setDateClicked(Date dateClicked) {
		this.dateClicked = dateClicked;
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "urlId", referencedColumnName = "Id")
	public UrlInfo getUrlinfo() {
		return urlInfo;
	}

	public void setUrlinfo(UrlInfo urlinfo) {
		this.urlInfo = urlinfo;
	}

}
