package me.derekmahar.example.spring.configuration;

public enum ApplicationPropertyName {
	JDBC_CONNECTION_DATABASE_NAME("jdbc.ConnectionDatabaseName"),
	JDBC_CONNECTION_PASSWORD("jdbc.ConnectionPassword"),
	JDBC_CONNECTION_PORT_NUMBER("jdbc.ConnectionPortNumber"),
	JDBC_CONNECTION_SERVER_NAME("jdbc.ConnectionServerName"),
	JDBC_CONNECTION_URL("jdbc.ConnectionUrl"),
	JDBC_CONNECTION_USER_NAME("jdbc.ConnectionUserName"),
	JMS_BROKER_URL("jms.brokerUrl"),
	JMS_BROKER_USER_NAME("jms.brokerUserName"),
	JMS_BROKER_PASSWORD("jms.brokerPassword");

	private final String propertyName;

	ApplicationPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

}
