package com.gr.qrapi.core.dao;


import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
public interface AccountDao extends GenericDao<Account, Integer> {
	Account checkLoginDetails(String username, String password);
}
