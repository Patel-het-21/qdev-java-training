package com.employeedatamanagement.config.multitenant;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;

/**
 * @author Het Patel
 * @since 27/01/26
 * 
 * Configuration class for setting up Hibernate multi-tenancy.
 * <p>
 * This class configures Hibernate to support multi-tenant architecture
 * using database-level isolation. It registers the tenant-specific
 * {@link javax.persistence.EntityManagerFactory} and transaction manager.
 * </p>
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.employeedatamanagement.repository.employee", entityManagerFactoryRef = "tenantEntityManagerFactory", transactionManagerRef = "tenantTransactionManager")
public class HibernateMultiTenantConfig {

	/**
	 * Primary data source used to obtain tenant-specific connections.
	 */
	private final DataSource dataSource;

	/**
	 * Custom multi-tenant connection provider implementation responsible
	 * for providing database connections based on the current tenant.
	 */
	private final MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl;

	/**
	 * Resolver used by Hibernate to determine the current tenant identifier
	 * for each request.
	 */
	private final TenantIdentifierResolver tenantIdentifierResolver;

	/**
	 * Constructor-based dependency injection for multi-tenant configuration.
	 *
	 * @param dataSource the primary {@link DataSource}
	 * @param multiTenantConnectionProviderImpl implementation for resolving tenant-specific connections
	 * @param tenantIdentifierResolver resolver for determining current tenant identifier
	 */
	@Autowired
	public HibernateMultiTenantConfig(DataSource dataSource, MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl, TenantIdentifierResolver tenantIdentifierResolver) {
		this.dataSource = dataSource;
		this.multiTenantConnectionProviderImpl = multiTenantConnectionProviderImpl;
		this.tenantIdentifierResolver = tenantIdentifierResolver;
	}

	/**
	 * Creates and configures the {@link LocalContainerEntityManagerFactoryBean}
	 * for tenant-specific entity management.
	 * <p>
	 * Hibernate multi-tenancy is enabled here by providing the required
	 * multi-tenant connection provider and tenant identifier resolver.
	 * </p>
	 *
	 * @param builder the {@link EntityManagerFactoryBuilder} used to build the entity manager factory
	 * @return the configured {@link LocalContainerEntityManagerFactoryBean} for tenant entities
	 */
	@Bean(name = "tenantEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.multiTenancy", "DATABASE");
		props.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProviderImpl);
		props.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);

		return builder.dataSource(dataSource).packages("com.employeedatamanagement.entity.tenant").properties(props).persistenceUnit("tenant").build();
	}

	/**
	 * Creates the transaction manager for tenant-specific persistence operations.
	 *
	 * @param emf the {@link EntityManagerFactory} associated with tenant EntityManagerFactory
	 * @return a {@link PlatformTransactionManager} for tenant transactions
	 */
	@Bean(name = "tenantTransactionManager")
	@Primary
	public PlatformTransactionManager tenantTransactionManager(@Qualifier("tenantEntityManagerFactory") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	/**
	 * Logs a message after the tenant EntityManagerFactory configuration is loaded.
	 * <p>
	 * This method runs after bean initialization and is useful for verifying
	 * that the multi-tenant JPA configuration has been successfully loaded.
	 * </p>
	 */
	@PostConstruct
	public void log() {
		System.out.println(">>> Tenant EntityManagerFactory config loaded");
	}

}