package com.employeedatamanagement.config.multitenant;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Multi-tenant connection provider implementation for Hibernate.
 * <p>
 * This class is responsible for supplying database connections
 * based on the current tenant identifier. It uses a single
 * {@link DataSource} and dynamically switches the database (catalog)
 * at the connection level to support database-based multi-tenancy.
 * </p>
 */
@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	/**
	 * Primary data source used to obtain database connections.
	 */
	private final DataSource dataSource;

	/**
	 * Constructs the multi-tenant connection provider with the given data source.
	 *
	 * @param dataSource the primary {@link DataSource} used for all tenant connections
	 */
	@Autowired
	public MultiTenantConnectionProviderImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Returns a default data source when no specific tenant is resolved.
	 * <p>
	 * This is typically used for bootstrapping or fallback scenarios.
	 * </p>
	 *
	 * @return the default {@link DataSource} (master connection)
	 */
	@Override
	protected DataSource selectAnyDataSource() {
		return dataSource; // master connection
	}

	/**
	 * Returns the data source for the given tenant identifier.
	 * <p>
	 * In this implementation, the same connection pool is reused,
	 * and the actual tenant database is selected at the connection level.
	 * </p>
	 *
	 * @param tenantIdentifier the current tenant identifier
	 * @return the {@link DataSource} used to obtain tenant connections
	 */
	@Override
	protected DataSource selectDataSource(Object tenantIdentifier) {
		return dataSource; // same pool, we switch DB at connection level
	}

	/**
	 * Obtains a database connection for the specified tenant.
	 * <p>
	 * The database (catalog) is switched dynamically based on the
	 * provided tenant identifier. If the tenant identifier is null
	 * or blank, a default database is selected.
	 * </p>
	 *
	 * @param tenantIdentifier the current tenant identifier
	 * @return a {@link Connection} configured for the tenant database
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public Connection getConnection(Object tenantIdentifier) throws SQLException {
		Connection connection = dataSource.getConnection();
//		connection.setCatalog(tenantIdentifier.toString()); // Switch MySQL database
		String db = (tenantIdentifier != null && !tenantIdentifier.toString().isBlank()) ? tenantIdentifier.toString() : "employee_spring";
	    System.out.println(">>> Switching to tenant DB: " + db);

		connection.setCatalog(db);
		return connection;
	}

	/**
	 * Releases the database connection after tenant-specific operations.
	 * <p>
	 * The connection is reset back to the default (master) database
	 * before being returned to the connection pool.
	 * </p>
	 *
	 * @param tenantIdentifier the current tenant identifier
	 * @param connection the active {@link Connection} to be released
	 * @throws SQLException if a database access error occurs while releasing the connection
	 */
	@Override
	public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
		connection.setCatalog("employee_spring"); // reset to master
		connection.close();
	}

}