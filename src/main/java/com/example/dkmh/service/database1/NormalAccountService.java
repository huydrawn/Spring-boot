package com.example.dkmh.service.database1;

import java.util.Optional;

import com.example.dkmh.model.account.NormalAccount;

public interface NormalAccountService {
	boolean existsByUsername(String username);

	void save(NormalAccount normalAccount);

	Optional<NormalAccount> findByUsername(String username);

	void delete(int id);
	
	Optional<NormalAccount> findByID(int id);

}
