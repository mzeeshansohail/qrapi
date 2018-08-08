package com.gr.qrapi.ws.v1;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gr.qrapi.core.model.Account;



/**
 * @author ufarooq
 */
@Path("/accounts")
public class AccountResource extends BaseResource {

	@POST
	public Response checkLoginDetails(Account account) {
		Account acc = getAccountService().checkLoginDetails(account.getUsername(),account.getPassword());
		if(acc == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity(acc).build();
	}
}