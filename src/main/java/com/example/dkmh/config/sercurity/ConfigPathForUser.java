package com.example.dkmh.config.sercurity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.dkmh.config.CustomLoginSuccessHandler;
import com.example.dkmh.config.authentication.AuthenticationUserProvider;
import com.example.dkmh.config.jwt.JwtAuthenticationEntryPoint;
import com.example.dkmh.config.jwt.JwtAuthenticationFilter;
import com.example.dkmh.config.jwt.JwtUtils;
import com.example.dkmh.service.database1.GoogleOAuth2UserService;
import com.example.dkmh.service.database1.UserService;

@Configuration
@EnableWebSecurity
public class ConfigPathForUser {
	@Autowired
	AuthenticationUserProvider auProvider;
	@Autowired
	GoogleOAuth2UserService googleOAuth2UserService;
//	@Autowired
//	RedisIndexedSessionRepository redisIndexedSessionRepository;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserService userService;
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private OAuth2AuthorizedClientRepository authorizedClientRepository;

	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager() {
		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.authorizationCode().refreshToken().build();

		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, authorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}

	@Bean
	public JwtAuthenticationFilter setJwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(jwtUtils, userService);
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(auProvider);
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

//	@Bean
//	public SessionRegistry sessionRegistry() {
//		// TODO Auto-generated method stub
//		return new SpringSessionBackedSessionRegistry<>(redisIndexedSessionRepository);
//	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public SecurityFilterChain configuePath(HttpSecurity http) throws Exception {

//		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1)
//				.maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry()).and().sessionFixation()
//				.migrateSession().invalidSessionUrl("/login?timeout").and().authorizeHttpRequests()
//				.antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/update", "/saveAccount", "/signin", "/visited").permitAll()
//				.antMatchers("/oauth2/authorization/google").permitAll().anyRequest().authenticated().and().logout()
//				.logoutUrl("/logout");
//		http.authorizeHttpRequests().antMatchers("/login", "/oauth2/oauthorization/google").permitAll().anyRequest()
//				.authenticated().and().formLogin().and().oauth2Login()
//				.successHandler(new AuthenticationSuccessHandler() {
//
//					@Override
//					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//							Authentication authentication) throws IOException, ServletException {
//						System.out.println("successs");
//
//					}
//				}).failureHandler(new AuthenticationFailureHandler() {
//
//					@Override
//					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//							AuthenticationException exception) throws IOException, ServletException {
//						System.out.println(exception);
//
//					}
//				});
//
//		return http.build();
		http.csrf().disable().cors().and().exceptionHandling()
				.authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers("/employee/**", "/product/**").hasRole("EMPLOYEE").antMatchers("/admin/**")
				.hasRole("ADMIN")
				.antMatchers("/", "/email/**", "/login/*", "/register/**", "/api/auth/**", "/product/getLists",
						"/oauth2/authorization/google")
				.permitAll().anyRequest().authenticated().and()
				.addFilterBefore(this.setJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/api/auth/logout").clearAuthentication(true).invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").logoutSuccessHandler((request, response, authentication) -> {
					response.setStatus(HttpServletResponse.SC_OK);
				});

//				.formLogin().loginPage("/login").successHandler(this.loginSuccessHandler()).loginPage("/login").and().logout()
//				.logoutUrl("/logout")
//		http.authorizeRequests().accessDecisionManager(new ConfigDecisionManager());
//		http.oauth2Login().userInfoEndpoint().userService(googleOAuth2UserService);

		return http.build();
	}

	@Bean
	public RoleHierarchy setRoleAuthoried() {
		RoleHierarchyImpl roles = new RoleHierarchyImpl();
		roles.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE > ROLE_USER");
		return roles;
	}

}
