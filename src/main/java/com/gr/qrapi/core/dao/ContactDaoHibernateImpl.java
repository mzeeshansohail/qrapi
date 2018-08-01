package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.AlertProfileLocation;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
public class ContactDaoHibernateImpl extends AbstractHibernateDao<Contact, Integer> implements ContactDao {

	private final Logger logger = LoggerFactory.getLogger(ContactDaoHibernateImpl.class);
	public static ContactDao getDao() {
		return DaoManager.getInstance().getDao(ContactDao.class);
	}

	@Override
	public void addContact(Contact contact) {
		try {
			Session session = getSession();
			session.save(contact);
			//list of account alert profiles 
			List<AlertProfile> pro= contact.getAccount().getAlertProfiles();
			String city=contact.getAddress().getCity();
			String country= contact.getAddress().getCountry();
			for(AlertProfile alert: pro) {
				List<AlertProfileLocation> list= alert.getLocation();
				for(AlertProfileLocation location: list) {
					if(location.getCity().equals(city) || location.getCountry().equals(country)) {
						System.out.print("Sending an ALert to the Contact:"+ contact);
						break;
					}
				}
			}
		}catch(Exception aex) {
			logger.error("Exception:", aex);
			throw new DaoException(aex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllContacts() {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			criteria.setMaxResults(100);
			List<Contact> contacts = (List<Contact>) criteria.list();
			return contacts;
		} catch (Exception aex) {
			logger.error("Exception:", aex);
			throw new DaoException(aex);
		}
	}

	@Override
	public void updateContact(int id, Contact contact) {
		try {
			Session session = getSession();
			contact.setId(id);
			session.update(contact);
		/*	AlertProfile alert = new AlertProfile();
			if(alert.getLocation().getCity().equals(contact.getAddress().getCity()) || alert.getLocation().getCountry().equals(contact.getAddress().getCity())) {
				System.out.println("Sending an alert to the Account: " + contact.getAccount());
			}
		*/}
			catch(Exception aex) {
				logger.error("Exception:", aex);
				throw new DaoException(aex);
			}		
	}

	@Override
	public void deleteContact(int id) {
		try {
			Session session = getSession();
			Contact contact= new Contact();
			contact.setId(id);
			session.delete(contact);
			}
			catch (Exception aex) {
				logger.error("Exception:", aex);
				throw new DaoException(aex);
			}
				
	}

	@Override
	public Contact getContactById(int id) {
		try {
			Session session= getSession();
			Contact contact= (Contact) session.get(Contact.class, id);
			return contact;
			
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}
}
