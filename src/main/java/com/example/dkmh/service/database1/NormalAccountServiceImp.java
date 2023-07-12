package com.example.dkmh.service.database1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.respository.database1.NormalAccountRepository;

@Service
public class NormalAccountServiceImp implements NormalAccountService {
	@Autowired
	NormalAccountRepository normalAccountRepository;

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return normalAccountRepository.existsByUsername(username);
	}

	@Override
	public void save(NormalAccount normalAccount) {
		normalAccountRepository.save(normalAccount);
	}

	@Override
	public Optional<NormalAccount> findByUsername(String username) {

		return normalAccountRepository.findByUsername(username);
	}

	@Override
	public void delete(int id) {
		normalAccountRepository.deleteById(id);
	}

	@Override
	public Optional<NormalAccount> findByID(int id) {
		// TODO Auto-generated method stub
		return normalAccountRepository.findById(id);
	}

}
