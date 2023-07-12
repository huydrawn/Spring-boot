package com.example.dkmh.service.database1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.user.User;
import com.example.dkmh.respository.database1.AccountRepository;
import com.example.dkmh.respository.database1.NormalAccountRepository;
import com.example.dkmh.respository.database1.UserReposistory;
import com.example.dkmh.respository.database1.UsersDetails;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	NormalAccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NormalAccount account = accountRepository.findByUsername(username).get();
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UsersDetails(account);
	}

}
