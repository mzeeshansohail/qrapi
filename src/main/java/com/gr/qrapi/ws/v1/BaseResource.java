package com.gr.qrapi.ws.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gr.qrapi.core.service.AccountService;
import com.gr.qrapi.core.service.AccountServiceLocal;
import com.gr.qrapi.core.service.ContactService;
import com.gr.qrapi.core.service.ContactServiceLocal;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaseResource {

	AccountServiceLocal accountService = AccountService.getService();

	protected AccountServiceLocal getAccountService() {
		return accountService;
	}

	ContactServiceLocal contactService = ContactService.getService();

	protected ContactServiceLocal getContactService() {
		return contactService;
	}
}