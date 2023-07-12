package com.example.dkmh.service.database1;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.customer.CustomerFamilier;
import com.example.dkmh.model.user.User;
import com.example.dkmh.respository.database1.AccountRepository;
import com.example.dkmh.respository.database1.GoogleAcountRepository;

// lưu session khi đăng nhập thành công bằng Google
@Service
public class GoogleOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	GoogleAcountRepository googleAcountRepository;
	@Autowired
	AccountRepository accountRepository;
	private HttpSession session;

	public GoogleOAuth2UserService(HttpSession httpSession) {
		this.session = httpSession;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("hể");
		OAuth2User user = super.loadUser(userRequest);
		String email = user.getAttribute("email");
		Optional<GoogleAccount> googleAccount = googleAcountRepository.findByEmail(email);
		GoogleAccount account;
		if (googleAccount.isPresent()) {
			account = googleAccount.get();
		} else {
			account = new GoogleAccount();
			User u = new CustomerFamilier(user.getAttribute("name"), email);
			account.setUser(u);
			account.setEmail(email);
		}
		account.setToken(userRequest.getAccessToken().getTokenValue());
		accountRepository.save(account);
		return user;
	}

}
