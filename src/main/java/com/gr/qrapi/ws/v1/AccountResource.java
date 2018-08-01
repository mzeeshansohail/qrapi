package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
@Path("/accounts")
public class AccountResource extends BaseResource {

	@GET
	public List<Account> getAllAccounts() {

		return getAccountService().getAllAccounts();
	}

	@POST
	public void addAccount(Account account) {
		getAccountService().addAccount(account);
	}

	@PUT
	@Path("/{id}")
	public void updateAccount(@PathParam("id") int id, Account account) {
		getAccountService().updateAccount(id, account);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteAccount(@PathParam("id")int id) {
		getAccountService().deleteAccount(id);
	}
	
	@GET
	@Path("/{id}")
	public Account getAccountById(@PathParam("id") int id) {
		return getAccountService().getAccountById(id);
	}
}