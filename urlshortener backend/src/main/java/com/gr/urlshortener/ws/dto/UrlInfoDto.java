package com.gr.urlshortener.ws.dto;

import java.io.Serializable;

public class UrlInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String originalUrl;
	private String shortUrl;
	private String createdOn;
	private int totalClicksCount;

	public UrlInfoDto() {
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public int getTotalClicksCount() {
		return totalClicksCount;
	}

	public void setTotalClicksCount(int totalClicksCount) {
		this.totalClicksCount = totalClicksCount;
	}
}
