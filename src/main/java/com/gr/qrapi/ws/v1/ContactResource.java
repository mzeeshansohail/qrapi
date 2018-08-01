package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
@Path("/contacts")
public class ContactResource extends BaseResource {

	@POST
	public void addContact(Contact contact) {
		getContactService().addContact(contact);
	}


	@GET
	public List<Contact> getAllContacts() {

		return getContactService().getAllContacts();
	}
	
	@PUT
	@Path("/{id}")
	public void updateContact(@PathParam("id") int id, Contact Contact) {
		getContactService().updateContact(id, Contact);
	}

	@DELETE
	@Path("/{id}")
	public void deleteAccount(@PathParam("id")int id){
		getContactService().deleteContact(id);
	}
	
	@GET
	@Path("/{id}")
	public void getContactById(@PathParam("id") int id) {
		getContactService().getContactById(id);
	}
}