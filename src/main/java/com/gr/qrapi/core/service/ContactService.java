package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.regexp.recompile;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
@Stateless
public class ContactService implements ContactServiceLocal {

	public static ContactServiceLocal getService() {
		return (ContactServiceLocal) ServiceManager.getService(ContactServiceLocal.class);
	}

	@Override
	public void addContact(Contact contact) {
		
		ContactDaoHibernateImpl.getDao().addContact(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return ContactDaoHibernateImpl.getDao().getAllContacts();
	}

	@Override
	public void updateContact(int id, Contact contact) {	
		ContactDaoHibernateImpl.getDao().updateContact(id, contact);
	}

	@Override
	public void deleteContact(int id) {
		ContactDaoHibernateImpl.getDao().deleteContact(id);
	}

	@Override
	public Contact getContactById(int id) {
		return ContactDaoHibernateImpl.getDao().getContactById(id);
	}
	
	
}
