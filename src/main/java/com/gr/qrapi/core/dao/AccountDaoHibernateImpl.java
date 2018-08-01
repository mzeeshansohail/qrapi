package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
public class AccountDaoHibernateImpl extends AbstractHibernateDao<Account, Integer> implements AccountDao {

	private final Logger logger = LoggerFactory.getLogger(AccountDaoHibernateImpl.class);
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
			logger.error("Exception: ",aex);
			throw new DaoException(aex);	
		}
	}

	@Override
	public void addAccount(Account account) {
		try {
			Session session = getSession();
			session.save(account);
		}catch(Exception aex) {
			logger.error("Exception:", aex);
			throw new DaoException(aex);	
		}
	}

	@Override
	public void updateAccount(int id, Account account) {
		try {
			Session session = getSession();
			account.setId(id);
			session.update(account);
		}
			catch(Exception aex) {
				logger.error("Exception:", aex);
				throw new DaoException(aex);	
			}

	}

	@Override
	public void deleteAccount(int id) {
			try {
				Session session = getSession();
				Account account= new Account();
				account.setId(id);
				session.delete(account);
				}
				catch (Exception aex) {
					logger.error("Exception:", aex);
					throw new DaoException(aex);	
				}		
	}

	@Override
	public Account getAccountById(int id) {
		try {
		Session session = getSession();
		Account account= (Account) session.get(Account.class, id);
		return account;
		}catch(Exception aex) {
			throw new DaoException(aex);
		}
	}
}
