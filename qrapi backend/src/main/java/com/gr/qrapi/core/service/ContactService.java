package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;


import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Address;
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
	public List<Contact> getContactByAccountId(int id) {
		return ContactDaoHibernateImpl.getDao().getContactByAccountId(id);
	}

	@Override
	public boolean deleteContact(int id, int address_id) {
		return ContactDaoHibernateImpl.getDao().deleteContact(id, address_id);
	}

	@Override
	public boolean updateContact(int id, Contact contact) {
		return ContactDaoHibernateImpl.getDao().updateContact(id, contact);
	}

	@Override
	public Contact getContactById(int id) {
		return ContactDaoHibernateImpl.getDao().getContactById(id);
	}

	@Override
	public boolean addContact(Contact contact, int id) {
		return ContactDaoHibernateImpl.getDao().addContact(contact,id);
	}

	@Override
	public List<Address> getAddressesByAccountId(int id) {
		return ContactDaoHibernateImpl.getDao().getAddressesByAccountId(id);
	}
	
	
	
}
