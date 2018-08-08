package com.gr.qrapi.core.service;


import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;
/**
 * @author ufarooq
 */
@Local
public interface AccountServiceLocal {
	Account checkLoginDetails(String username,String password);
}
