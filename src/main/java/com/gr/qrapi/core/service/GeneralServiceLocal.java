package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
@Local
public interface GeneralServiceLocal {

	List<Account> getAllAccounts();
}
