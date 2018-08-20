package com.gr.urlshortener.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.urlshortener.core.model.Clicks;
import com.gr.urlshortener.core.model.UrlInfo;
import com.gr.urlshortener.ws.dto.GraphDataDto;
import com.gr.urlshortener.ws.dto.UrlInfoDto;

@Local
public interface UrlServiceLocal {
	List<UrlInfo> getAllUrlInfos();

	String shortenUrl(String longUrl);

	UrlInfo getLongUrl(String shortUrl);

	boolean addClicks(Clicks clicks);

	int getCount(int id);

	boolean incrementClickInUrlInfo(int count, int id);

	GraphDataDto getBrowsersData(int id);

	GraphDataDto getPlatformsData(int id);

	GraphDataDto getClicksData(int id);

	UrlInfoDto getUrlInfo(int id);
}
