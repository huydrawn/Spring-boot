package com.example.dkmh.config;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.respository.database1.NormalAccountRepository;

// lưu phiên đăng nhập khi đăng nhập thành công
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	NormalAccountRepository normalAccountRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("user", normalAccountRepository.findByUsername(authentication.getName()));
		response.sendRedirect(request.getContextPath() + "/");
	}

}
