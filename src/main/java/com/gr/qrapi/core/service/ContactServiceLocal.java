package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
@Local
public interface ContactServiceLocal {
	void addContact(Contact contact);
	List<Contact> getAllContacts();
	void updateContact(int id, Contact contact);
	void deleteContact(int id);
	Contact getContactById(int id);
}
