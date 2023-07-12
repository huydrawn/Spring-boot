package com.example.dkmh.config.sercurity.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.web.http.SessionRepositoryFilter;

import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.user.User;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds =5)
public class SessionConfig {

//	@Bean
//	public Map<Account, AtomicInteger> sessionCountMap() {
//		return new ConcurrentHashMap<>();
//	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public RedisOperations<Object, Object> sessionRedisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(this.redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	@Primary
	public RedisIndexedSessionRepository sessionRepository1(RedisConnectionFactory redisConnectionFactory,
			Map<Account, AtomicInteger> sessionCountMap) {
		RedisIndexedSessionRepository sessionRepository = new RedisIndexedSessionRepository(
				this.sessionRedisTemplate());
		sessionRepository.setDefaultMaxInactiveInterval(5);
		sessionRepository.setApplicationEventPublisher(event -> {
			if (event instanceof SessionDestroyedEvent) {
				SessionDestroyedEvent destroyedEvent = (SessionDestroyedEvent) event;
				String sessionId = destroyedEvent.getSessionId();
				if (sessionId != null) {
					Account user = destroyedEvent.getSession().getAttribute("user");
					if (user != null) {
						AtomicInteger sessionCount = sessionCountMap.get(user);
						if (sessionCount != null) {
							sessionCount.decrementAndGet();
						}
					}
				}
			}
		});
		return sessionRepository;
	}
//
//	@Bean
//	@Primary
//	public FilterRegistrationBean<SessionRepositoryFilter<?>> sessionRepositoryFilterRegistration1(
//			SessionRepositoryFilter<?> sessionRepositoryFilter) {
//		FilterRegistrationBean<SessionRepositoryFilter<?>> registration = new FilterRegistrationBean<>();
//		registration.setFilter(sessionRepositoryFilter);
//		registration.addUrlPatterns("/*");
//		registration.setName("sessionRepositoryFilter");
//		return registration;
//	}
//
//	@Bean
//	public HttpSessionListener httpSessionListener(Map<Account, AtomicInteger> sessionCountMap) {
//		return new HttpSessionListener() {
//			@Override
//			public void sessionCreated(HttpSessionEvent se) {
//				Account user = (Account) se.getSession().getAttribute("user");
//				if (user != null) {
//					sessionCountMap.computeIfAbsent(user, k -> new AtomicInteger()).incrementAndGet();
//				}
//			}
//
//			@Override
//			public void sessionDestroyed(HttpSessionEvent se) {
//				Account user = (Account) se.getSession().getAttribute("user");
//				if (user != null) {
//					sessionCountMap.computeIfPresent(user, (k, v) -> {
//						int count = v.decrementAndGet();
//						return count > 0 ? v : null;
//					});
//				}
//			}
//		};
//	}
}