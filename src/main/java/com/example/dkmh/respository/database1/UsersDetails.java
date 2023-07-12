package com.example.dkmh.respository.database1;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.customer.Customer;
import com.example.dkmh.model.employee.Admin;
import com.example.dkmh.model.employee.Employee;
import com.example.dkmh.model.user.User;

public class UsersDetails implements UserDetails {
	NormalAccount account;

	public UsersDetails(NormalAccount account) {
		super();
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (account.getUser() instanceof Customer) {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		} else if (account.getUser() instanceof Admin) {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (account.getUser() instanceof Employee) {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
		}
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
