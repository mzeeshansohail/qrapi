package com.gr.qrapi.core.dao;


import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
public interface ContactDao extends GenericDao<Contact, Integer> {
	List<Contact> getContactByAccountId(int id);
	boolean deleteContact(int id, int address_id);
	boolean updateContact(int id, Contact contact);
	Contact getContactById(int id);
	boolean addContact(Contact contact, int id);
	List<Address> getAddressesByAccountId(int id);
}
