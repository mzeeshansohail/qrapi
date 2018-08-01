package com.gr.qrapi.core.dao;


import java.util.List;
import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
public interface AccountDao extends GenericDao<Account, Integer> {
	List<Account> getAllAccounts();
	void addAccount(Account account);
	void updateAccount(int id, Account account);
	void deleteAccount(int id);
	Account getAccountById(int id);
}
