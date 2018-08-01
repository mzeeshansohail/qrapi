package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.AccountDaoHibernateImpl;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
@Stateless
public class AccountService implements AccountServiceLocal {

	public static AccountServiceLocal getService() {
		return (AccountServiceLocal) ServiceManager.getService(AccountServiceLocal.class);
	}

	@Override
	public List<Account> getAllAccounts() {
		return AccountDaoHibernateImpl.getDao().getAllAccounts();
	}

	@Override
	public void addAccount(Account account) {
		AccountDaoHibernateImpl.getDao().addAccount(account);
	}

	@Override
	public void updateAccount(int id, Account account) {
		AccountDaoHibernateImpl.getDao().updateAccount(id, account);
	}

	@Override
	public void deleteAccount(int id) {
		AccountDaoHibernateImpl.getDao().deleteAccount(id);
	}

	@Override
	public Account getAccountById(int id) {
		return AccountDaoHibernateImpl.getDao().getAccountById(id);
	}
	
	
}
