package com.gr.urlshortener.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.urlshortener.core.dao.UrlInfoDaoHibernateImpl;
import com.gr.urlshortener.core.model.Clicks;
import com.gr.urlshortener.core.model.UrlInfo;
import com.gr.urlshortener.ws.dto.GraphDataDto;
import com.gr.urlshortener.ws.dto.UrlInfoDto;
import com.gr.urlshortener.ws.util.DateUtil;

@Stateless
public class UrlService implements UrlServiceLocal {

	public static UrlServiceLocal getService() {
		return (UrlServiceLocal) ServiceManager.getService(UrlServiceLocal.class);
	}

	@Override
	public List<UrlInfo> getAllUrlInfos() {
		return UrlInfoDaoHibernateImpl.getDao().getAllUrlInfos();
	}

	@Override
	public String shortenUrl(String longUrl) {
		int month = 1;
		Date creationDate = new Date();
		java.sql.Date date= new java.sql.Date(creationDate.getTime());
		System.out.println(date);
		String url = UrlInfoDaoHibernateImpl.getDao().getShortUrl(longUrl);

		if (url != null) {
			return url;
		}
		String shortenedUrl = "http://localhost:8080/urlshortener/"+UrlShortener.getRandomString();
		UrlInfo urlInfo = new UrlInfo();
		urlInfo.setOriginalUrl(longUrl);
		urlInfo.setShortUrl(shortenedUrl);
		System.out.println(date);
		urlInfo.setCreatedOn(date);
		urlInfo.setExpiryDate(DateUtil.addMonthInDate(date, month));

		String status = UrlInfoDaoHibernateImpl.getDao().save(urlInfo);

		if ("Success".equals(status)) {
			return "success";
		}
		return null;
	}

	@Override
	public UrlInfo getLongUrl(String shortUrl) {
		UrlInfo urlInfo = UrlInfoDaoHibernateImpl.getDao().getLongUrl(shortUrl);
		if (urlInfo != null) {
			return urlInfo;
		}
		return null;
	}

	@Override
	public boolean addClicks(Clicks clicks) {
		boolean added = UrlInfoDaoHibernateImpl.getDao().addClicks(clicks);
		if (added) {
			return true;
		}
		return false;
	}

	@Override
	public int getCount(int id) {
		return UrlInfoDaoHibernateImpl.getDao().getCount(id);
	}

	@Override
	public boolean incrementClickInUrlInfo(int count, int id) {
		return UrlInfoDaoHibernateImpl.getDao().incrementClickInUrlInfo(count, id);
	}

	@Override
	public GraphDataDto getBrowsersData(int id) {
		String dataToRead = "browser";
		List<String> browsers = UrlInfoDaoHibernateImpl.getDao().getAllDistinctEntries(id, dataToRead);
		List<Long> data = new ArrayList<Long>();
		for (String browser : browsers) {
			Long count = UrlInfoDaoHibernateImpl.getDao().getDataFromTable(id, dataToRead, browser);
			data.add(count);
		}
		GraphDataDto graphData = new GraphDataDto();
		graphData.setData(data);
		graphData.setLabels(browsers);
		return graphData;
	}

	@Override
	public GraphDataDto getPlatformsData(int id) {
		String dataToRead = "platform";
		List<String> platforms = UrlInfoDaoHibernateImpl.getDao().getAllDistinctEntries(id, dataToRead);
		List<Long> data = new ArrayList<Long>();
		for (String platform : platforms) {
			Long count = UrlInfoDaoHibernateImpl.getDao().getDataFromTable(id, dataToRead, platform);
			data.add(count);
		}
		GraphDataDto graphData = new GraphDataDto();
		graphData.setData(data);
		graphData.setLabels(platforms);
		return graphData;
	}

	@Override
	public GraphDataDto getClicksData(int id) {
		String dataToRead = "dateClicked";

		/**
		 * dates to query the count from database
		 */
		List<String> dates = UrlInfoDaoHibernateImpl.getDao().getAllDistinctEntries(id, dataToRead);
		List<Long> data = new ArrayList<Long>();

		/**
		 * Labels to only store the year from Date
		 */
		List<String> labels = new ArrayList<>();

		for (String dateString : dates) {
			Long count = UrlInfoDaoHibernateImpl.getDao().getDataFromTable(id, dataToRead, dateString);
			String Year = dateString.substring(0, 4);
			if (labels.contains(Year)) {
				Long prevCount = data.get(labels.indexOf(Year));
				data.set(labels.indexOf(Year), count + prevCount);
			} else {
				labels.add(dateString.substring(0, 4));
				data.add(count);
			}

		}
		GraphDataDto graphData = new GraphDataDto();
		graphData.setData(data);
		graphData.setLabels(labels);
		return graphData;
	}

	@Override
	public UrlInfoDto getUrlInfo(int id) {
		UrlInfo urlInfo = UrlInfoDaoHibernateImpl.getDao().getUrlInfo(id);
		UrlInfoDto urlInfoDto = new UrlInfoDto();
		urlInfoDto.setOriginalUrl(urlInfo.getOriginalUrl());
		urlInfoDto.setCreatedOn(urlInfo.getCreatedOn().toString().substring(0, 10));
		urlInfoDto.setShortUrl(urlInfo.getShortUrl());
		urlInfoDto.setTotalClicksCount(urlInfo.getTotalClicksCount());
		return urlInfoDto;
		
	}

}
