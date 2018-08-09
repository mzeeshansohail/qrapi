package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

/**
 * @author ufarooq
 */
@Path("/contacts")
public class ContactResource extends BaseResource {

	@POST
	@Path("/{id}")
	public Response addContact(Contact contact, @PathParam("id")int id) {
		boolean checker= getContactService().addContact(contact, id);
		if(checker==false) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity("Contact Added").build();
	}

	@GET
	@Path("/{id}")
	public Response getContactByAccountId(@PathParam("id") int id) {
		List<Contact> contacts= getContactService().getContactByAccountId(id);
		if(contacts==null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity(contacts).build();
	}
	
	@GET
	@Path("addresses/{id}")
	public Response getAddressesByAccountId(@PathParam("id") int id) {
		List<Address> addresses= getContactService().getAddressesByAccountId(id);
		if(addresses==null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity(addresses).build();
	}
	
	@DELETE
	@Path("/{id}/{address_id}")
	public Response deleteContact(@PathParam("id") int id,@PathParam("address_id") int address_id) {
		boolean checker= getContactService().deleteContact(id,address_id);
		if(!checker) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response updateContact(@PathParam("id")int id, Contact contact) {
		boolean checker= getContactService().updateContact(id, contact);
		if(!checker)
		return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/contactId/{id}")
	public Response getContactById(@PathParam("id") int id) {
		Contact contact= getContactService().getContactById(id);
		if(contact==null) 
			return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).entity(contact).build();
	}
}