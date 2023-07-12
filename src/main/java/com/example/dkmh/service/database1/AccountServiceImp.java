package com.example.dkmh.service.database1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.respository.database1.AccountRepository;
import com.example.dkmh.respository.database1.NormalAccountRepository;

@Service
public class AccountServiceImp implements AccountService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	NormalAccountRepository normalAccountRepository;

	@Override
	public void save(NormalAccount acc) {
		acc.setPassword(passwordEncoder.encode(acc.getPassword()));
		accountRepository.save(acc);
	}

	@Override
	public void save(GoogleAccount acc) {
		accountRepository.save(acc);
	}

	@Override
	public boolean userIsExsit(String name) {
		return normalAccountRepository.findByUsername(name) != null;
	}

}
