package me.derekmahar.example.spring.configuration;

import org.springframework.core.env.Environment;

public class ApplicationEnvironment implements
		ApplicationPropertiesSource {

	private final Environment applicationEnvironment;

	public ApplicationEnvironment(Environment applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	@Override
	public String getJdbcConnectionDatabaseName() {
		return this.applicationEnvironment.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_DATABASE_NAME.getPropertyName());
	}

	@Override
	public String getJdbcConnectionPassword() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JDBC_CONNECTION_PASSWORD.
				getPropertyName());
	}

	@Override
	public int getJdbcConnectionPortNumber() {
		return Integer.parseInt(this.applicationEnvironment.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_PORT_NUMBER.getPropertyName()));
	}

	@Override
	public String getJdbcConnectionServerName() {
		return this.applicationEnvironment.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_SERVER_NAME.getPropertyName());
	}

	@Override
	public String getJdbcConnectionUrl() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JDBC_CONNECTION_URL.getPropertyName());
	}

	@Override
	public String getJdbcConnectionUserName() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JDBC_CONNECTION_USER_NAME.
				getPropertyName());
	}

	@Override
	public String getJdbcDataSourceClassName() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JDBC_DATA_SOURCE_CLASS_NAME.
				getPropertyName());
	}

	@Override
	public String getJmsBrokerPassword() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JDBC_CONNECTION_PASSWORD.
				getPropertyName());
	}

	@Override
	public String getJmsBrokerUrl() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JMS_BROKER_URL.getPropertyName());
	}

	@Override
	public String getJmsBrokerUserName() {
		return this.applicationEnvironment.getProperty(ApplicationPropertyName.JMS_BROKER_USER_NAME.getPropertyName());
	}
}
