package com.gr.urlshortener.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.urlshortener.core.model.Clicks;
import com.gr.urlshortener.core.model.UrlInfo;

public interface UrlInfoDao extends GenericDao<UrlInfo, Integer> {
	List<UrlInfo> getAllUrlInfos();

	String shortenUrl(UrlInfo urlInfo);

	String getShortUrl(String longUrl);

	UrlInfo getLongUrl(String shortUrl);

	boolean addClicks(Clicks clicks);

	int getCount(int id);

	boolean incrementClickInUrlInfo(int count, int id);

	List<String> getAllDistinctEntries(int id, String entryName);

	Long getDataFromTable(int id, String dataToRead, String data);

	UrlInfo getUrlInfo(int id);
}
