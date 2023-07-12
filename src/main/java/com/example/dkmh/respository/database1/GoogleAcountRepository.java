package com.example.dkmh.respository.database1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dkmh.model.account.GoogleAccount;

@Repository
public interface GoogleAcountRepository extends JpaRepository<GoogleAccount, Integer> {
	Optional<GoogleAccount> findByEmail(String email);
}
