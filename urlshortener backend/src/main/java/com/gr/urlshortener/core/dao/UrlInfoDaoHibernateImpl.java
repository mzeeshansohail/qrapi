package com.gr.urlshortener.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.urlshortener.core.model.Clicks;
import com.gr.urlshortener.core.model.UrlInfo;

public class UrlInfoDaoHibernateImpl extends AbstractHibernateDao<UrlInfo, Integer> implements UrlInfoDao {

	public static UrlInfoDao getDao() {
		return DaoManager.getInstance().getDao(UrlInfoDao.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlInfo> getAllUrlInfos() {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(UrlInfo.class);
			List<UrlInfo> list = (List<UrlInfo>) criteria.list();
			if (list.size() > 0) {
				return list;
			} else
				return null;

		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public String shortenUrl(UrlInfo urlInfo) {
		try {
			Session session = getSession();
			String shortenedUrl = urlInfo.getShortUrl();
			session.save(urlInfo);
			return shortenedUrl;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public int getCount(int id) {
		try {
			Session session = getSession();
			UrlInfo urlInfo = (UrlInfo) session.get(UrlInfo.class, id);
			return urlInfo.getTotalClicksCount();
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getShortUrl(String longUrl) {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(UrlInfo.class);
			criteria.add(Restrictions.eq("originalUrl", longUrl));
			List<UrlInfo> list = (List<UrlInfo>) criteria.list();
			if (list.size() > 0) {
				UrlInfo urlInfo = (UrlInfo) criteria.list().get(0);
				return urlInfo.getShortUrl();
			} else
				return null;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UrlInfo getLongUrl(String shortUrl) {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(UrlInfo.class);
			criteria.add(Restrictions.eq("shortUrl", "http://localhost:8080/urlshortener/" + shortUrl));
			List<UrlInfo> list = (List<UrlInfo>) criteria.list();
			if (list.size() > 0) {
				UrlInfo urlInfo = (UrlInfo) criteria.list().get(0);
				return urlInfo;
			}
			return null;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public boolean addClicks(Clicks clicks) {
		try {
			Session session = getSession();
			session.save(clicks);
			return true;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}

	}

	@Override
	public boolean incrementClickInUrlInfo(int count, int id) {
		try {
			Session session = getSession();
			UrlInfo urlInfo = (UrlInfo) session.get(UrlInfo.class, id);
			urlInfo.setTotalClicksCount(count);
			session.update(urlInfo);
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllDistinctEntries(int id, String entryName) {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Clicks.class);
			criteria.add(Restrictions.eq("urlinfo.id", id));
			criteria.setProjection(Projections.distinct(Projections.property(entryName)));
			if (entryName.equals("dateClicked")) {
				List<Date> dates = criteria.list();

				/**
				 * converting date to String getting time with string removing time by using
				 * substring
				 */
				List<String> dateString = new ArrayList<>();
				for (Date date : dates) {
					dateString.add(date.toString().substring(0, 10));
				}
				return dateString;
			}

			return (List<String>) criteria.list();
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public Long getDataFromTable(int id, String dataToRead, String data) {
		try {
			Session session = getSession();
			if (dataToRead.equals("dateClicked")) {
				Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(data);
				Criteria criteria = session.createCriteria(Clicks.class);
				criteria.add(Restrictions.eq("urlinfo.id", id));
				criteria.add(Restrictions.eq(dataToRead, date));
				criteria.setProjection(Projections.rowCount());
				return (Long) criteria.uniqueResult();
			}
			Criteria criteria = session.createCriteria(Clicks.class);
			criteria.add(Restrictions.eq("urlinfo.id", id));
			criteria.add(Restrictions.eq(dataToRead, data));
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public UrlInfo getUrlInfo(int id) {
		Session session = getSession();
		UrlInfo urlInfo = (UrlInfo) session.get(UrlInfo.class, id);
		return urlInfo;
	}
}