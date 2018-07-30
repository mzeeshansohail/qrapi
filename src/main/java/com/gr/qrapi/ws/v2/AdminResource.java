package com.gr.qrapi.ws.v2;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

/**
 * @author ufarooq
 */
@Path("/v1/accounts")
public class AdminResource {

	GeneralServiceLocal genericService = GeneralService.getService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Account> getAllAccounts() {
		
		return genericService.getAllAccounts();
	}
}
