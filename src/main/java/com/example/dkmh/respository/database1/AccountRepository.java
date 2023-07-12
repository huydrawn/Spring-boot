package com.example.dkmh.respository.database1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.NormalAccount;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
}
