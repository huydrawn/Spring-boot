package com.example.dkmh.function.admin;

import java.util.List;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.user.User;

public interface FunctionAdmin {
	public void saveAccount(NormalAccount account);

	public void deteleAccount(int id);

	public void updateAccount(Account account);

	public Account readAccount(int id);

	public List<Account> getAccount();
}
