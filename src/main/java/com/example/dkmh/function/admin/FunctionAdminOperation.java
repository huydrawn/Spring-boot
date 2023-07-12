package com.example.dkmh.function.admin;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.respository.database1.AccountRepository;
import com.example.dkmh.respository.database1.GoogleAcountRepository;
import com.example.dkmh.respository.database1.NormalAccountRepository;
import com.example.dkmh.service.database1.AccountServiceImp;

@Repository
public class FunctionAdminOperation implements FunctionAdmin {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountServiceImp accountServiceImp;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void saveAccount(NormalAccount account) {
		accountServiceImp.save(account);
	}

	@Override
	public void deteleAccount(int id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void updateAccount(Account account) {
		Optional<Account> acc = accountRepository.findById(1);
		System.out.println();
		if (account instanceof GoogleAccount) {
			GoogleAccount a = (GoogleAccount) account;
			GoogleAccount googleAccount = (GoogleAccount) acc.get();
			googleAccount.setEmail(a.getEmail());
			googleAccount.setUser(a.getUser());
			googleAccount.setToken(a.getToken());
			accountServiceImp.save(googleAccount);
		} else {
			NormalAccount n = (NormalAccount) account;
			NormalAccount normalAccount = (NormalAccount) acc.get();
			normalAccount.setPassword(n.getPassword());
			normalAccount.setUser(n.getUser());
			normalAccount.setUsername(n.getUsername());
			accountServiceImp.save(normalAccount);
		}
	}

	@Override
	public Account readAccount(int id) {

		return accountRepository.findById(id).get();
	}

	@Override
	public List<Account> getAccount() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

}
