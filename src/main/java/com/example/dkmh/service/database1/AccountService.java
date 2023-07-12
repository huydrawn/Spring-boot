package com.example.dkmh.service.database1;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.account.NormalAccount;

public interface AccountService {
	void save(NormalAccount acc);

	void save(GoogleAccount acc);

	boolean userIsExsit(String name);

}
