package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

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
	public List<Account> getAllAccounts() {
		
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.setMaxResults(100);

			List<Account> accounts = (List<Account>) criteria.list();

			return accounts;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}
}
