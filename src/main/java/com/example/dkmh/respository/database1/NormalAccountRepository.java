package com.example.dkmh.respository.database1;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dkmh.config.emailVertification.EmailComfirmation;
import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.NormalAccount;

@Repository

public interface NormalAccountRepository extends JpaRepository<NormalAccount, Integer> {
	public Optional<NormalAccount> findByUsername(String username);

	boolean existsByUsername(String username);

}
