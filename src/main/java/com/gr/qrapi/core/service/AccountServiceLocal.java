package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
@Local
public interface AccountServiceLocal {
	List<Account> getAllAccounts();
	void addAccount(Account account);
	void updateAccount(int id, Account account);
	void deleteAccount(int id);
	Account getAccountById(int id);
}
