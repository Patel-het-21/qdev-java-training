package com.employeedatamanagement.config.master;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

/**
 * Configuration class for the master database JPA setup.
 * <p>
 * This configuration defines the {@link javax.persistence.EntityManagerFactory}
 * and {@link org.springframework.transaction.PlatformTransactionManager} used
 * for accessing master-level entities such as users and tenants.
 * </p>
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.employeedatamanagement.repository.master", entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterTransactionManager")
public class MasterJpaConfig {

	/**
	 * Creates and configures the EntityManagerFactory for the master database.
	 * <p>
	 * This EntityManagerFactory is marked as {@link Primary} and is used for
	 * managing master entities like User and Tenant.
	 * </p>
	 *
	 * @param builder the {@link EntityManagerFactoryBuilder} used to build the factory
	 * @param dataSource the {@link DataSource} connected to the master database
	 * @return the configured {@link LocalContainerEntityManagerFactoryBean} for master persistence unit
	 */
	@Bean(name = "masterEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.employeedatamanagement.entity.master") // User, Tenant
				.persistenceUnit("master").build();
	}

	/**
	 * Creates the transaction manager for the master persistence unit.
	 * <p>
	 * This transaction manager is responsible for handling transactional
	 * operations on master-level entities.
	 * </p>
	 *
	 * @param emf the {@link EntityManagerFactory} associated with the master EntityManagerFactory
	 * @return a {@link PlatformTransactionManager} for master transactions
	 */
	@Bean(name = "masterTransactionManager")
	public PlatformTransactionManager masterTransactionManager(@Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}