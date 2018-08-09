package com.gr.qrapi.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.fontbox.util.autodetect.WindowsFontDirFinder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
public class ContactDaoHibernateImpl extends AbstractHibernateDao<Contact, Integer> implements ContactDao {
	public static ContactDao getDao() {
		return DaoManager.getInstance().getDao(ContactDao.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContactByAccountId(int id) {
		try {
			Session session= getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			criteria.add(Restrictions.eq("account.id", Integer.valueOf(id)));
			List<Contact> list= (List<Contact>) criteria.list();
			if (list.size() > 0) {
	           	System.out.println("Contact exists. returning");
	           	return list;
			}
			else {
				System.out.println("Contact does not exist.");
				return null;	
			}
		}catch (Exception aex) {
			throw new DaoException(aex);
		}
	}
	

	@Override
	public boolean deleteContact(int id, int address_id) {
		try {
			Session session = getSession();
			Contact contact= new Contact();
			contact.setId(id);
			Address address= new Address();
			address.setId(address_id);
			contact.setAddress(address);
			session.delete(contact);
			return true;
			}
			catch (Exception aex) {
				throw new DaoException(aex);
			}
				
	}	

	@Override
	public boolean updateContact(int id, Contact contact) {
		try {
			Session session = getSession();
			contact.setId(id);
			session.update(contact);
			return true;
		}
			catch(Exception aex) {
				throw new DaoException(aex);
			}	
	}

	@Override
	public Contact getContactById(int id) {
		try{
			Session session= getSession();
			Contact contact= (Contact) session.get(Contact.class, id);
		if(contact==null)
			return null;
		return contact;
	}catch (Exception aex) {
		throw new DaoException(aex);
	}
	}
	
	
	public boolean addContact(Contact contact,int id) {
		try {
			Session session = getSession();
			Contact contact1 = new Contact();
			contact1.setFirstName(contact.getFirstName());
			contact1.setLastName(contact.getLastName());
			contact1.setEmail(contact.getEmail());
			contact1.setGender(contact.getGender());
			contact1.setPhoneNumber(contact.getPhoneNumber());
			contact1.setStatus(contact.getStatus());
			contact1.setAddress(contact.getAddress());
			Account account = new Account();
			account= contact.getAccount();
			account.setId(id);
			System.out.println("account"+ id);
			contact1.setAccount(account);
			session.save(contact1);
			return true;
		}catch(Exception aex) {
			throw new DaoException(aex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Address> getAddressesByAccountId(int id) {
		try {
			Session session= getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			criteria.add(Restrictions.eq("account.id", Integer.valueOf(id)));
			List<Contact> list= (List<Contact>) criteria.list();
			System.out.println("List of Contacts"+ list.size());
			List<Address> listOfAddresses= new ArrayList<Address>();
			for(int i=0;i< list.size();i++) {
				listOfAddresses.add(list.get(i).getAddress());
			}
			System.out.println("List of Contacts"+ listOfAddresses.size());
			return listOfAddresses;
		}catch (Exception aex) {
			throw new DaoException(aex);
		}
	}
	
	
}

