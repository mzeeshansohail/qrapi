package com.gr.qrapi.core.service;


import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
@Local
public interface ContactServiceLocal {
	List<Contact> getContactByAccountId(int id);
	boolean deleteContact(int id);
	boolean updateContact(int id,Contact contact);
	Contact getContactById(int id);
	boolean addContact(Contact contact,int id);
	List<Address> getAddressesByAccountId(int id);
}

