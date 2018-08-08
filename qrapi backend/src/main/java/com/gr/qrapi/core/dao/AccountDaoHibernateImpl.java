package com.gr.qrapi.core.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
public class AccountDaoHibernateImpl extends AbstractHibernateDao<Account, Integer> implements AccountDao {

	public static AccountDao getDao() {
		return DaoManager.getInstance().getDao(AccountDao.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Account checkLoginDetails(String username, String password) {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq("username", username));
			List<Account> list= (List<Account>) criteria.list();
			if (list.size() > 0) {
	           	Account account = (Account) criteria.list().get(0);	
			if(password.equals(account.getPassword())) {
				System.out.println(username+ " "+ password + " returning success ");
				return account;
			}
			else {
				System.out.println(username+ " "+ password + " returning error ");
				return null;
			}
			}
			else {
				System.out.println("username does not exist.");
				return null;
			}
		}catch(Exception aex) {
			throw new DaoException(aex);
		}
	}
}
