package me.derekmahar.example.spring.configuration;

import java.util.Properties;

public class ApplicationProperties implements ApplicationPropertiesSource {

	private final Properties applicationProperties;

	public ApplicationProperties(Properties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getJdbcConnectionDatabaseName() {
		return this.applicationProperties.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_DATABASE_NAME.getPropertyName());
	}

	@Override
	public String getJdbcConnectionPassword() {
		return this.applicationProperties.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_PASSWORD.getPropertyName());
	}

	@Override
	public int getJdbcConnectionPortNumber() {
		return Integer.parseInt(this.applicationProperties.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_PORT_NUMBER.getPropertyName()));
	}

	@Override
	public String getJdbcConnectionServerName() {
		return this.applicationProperties.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_SERVER_NAME.getPropertyName());
	}

	@Override
	public String getJdbcConnectionUrl() {
		return this.applicationProperties.getProperty(ApplicationPropertyName.JDBC_CONNECTION_URL.getPropertyName());
	}

	@Override
	public String getJdbcConnectionUserName() {
		return this.applicationProperties.getProperty(ApplicationPropertyName.JDBC_CONNECTION_USER_NAME.
				getPropertyName());
	}

	@Override
	public String getJmsBrokerPassword() {
		return this.applicationProperties.
				getProperty(ApplicationPropertyName.JDBC_CONNECTION_PASSWORD.getPropertyName());
	}

	@Override
	public String getJmsBrokerUrl() {
		return this.applicationProperties.getProperty(ApplicationPropertyName.JMS_BROKER_URL.getPropertyName());
	}

	@Override
	public String getJmsBrokerUserName() {
		return this.applicationProperties.getProperty(ApplicationPropertyName.JMS_BROKER_USER_NAME.getPropertyName());
	}
}
