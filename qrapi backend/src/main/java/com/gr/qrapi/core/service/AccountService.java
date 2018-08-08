package com.gr.qrapi.core.service;


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
	public Account checkLoginDetails(String username, String password) {
		return AccountDaoHibernateImpl.getDao().checkLoginDetails(username, password);
	}
	

	
}
