package com.example.dkmh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebSecurity
@EnableCaching
@EnableSpringDataWebSupport
//@EnableHypermediaSupport(type = { HypermediaType.HTTP_PROBLEM_DETAILS })
public class DkmhApplication {
	public static void main(String[] args) {
		SpringApplication.run(DkmhApplication.class, args);
	}
}
