package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
public interface ContactDao extends GenericDao<Contact, Integer> {
	void addContact(Contact Contact);
	List<Contact> getAllContacts();
	void updateContact(int id, Contact contact);
	void deleteContact(int id);
	Contact getContactById(int id);
}
