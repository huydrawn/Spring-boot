package com.example.dkmh.config.database;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

//@Configuration
//@PropertySource("classpath:application.properties")
public class DatabaseConfig {
//	@Value("${spring.datasource.url}")
	String url;

//	@Bean
	public void getA() {
		System.out.println(url);
		
	}

//	protected DatabaseConfig(DataSource dataSource, JpaProperties properties,
//			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
//		super(dataSource, properties, jtaTransactionManager);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected Map<String, Object> getVendorProperties() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}