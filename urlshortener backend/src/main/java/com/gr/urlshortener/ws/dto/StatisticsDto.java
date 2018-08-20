package com.gr.urlshortener.ws.dto;

import java.io.Serializable;
import java.util.HashMap;

public class StatisticsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UrlInfoDto urlInfo;
	private HashMap<String, GraphDataDto> stats;

	public UrlInfoDto getUrlInfo() {
		return urlInfo;
	}

	public void setUrlInfo(UrlInfoDto urlInfo) {
		this.urlInfo = urlInfo;
	}

	public HashMap<String, GraphDataDto> getStats() {
		return stats;
	}

	public void setStats(HashMap<String, GraphDataDto> stats) {
		this.stats = stats;
	}
}
