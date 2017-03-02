package me.derekmahar.example.spring.configuration;

public enum ApplicationPropertyName {
	JDBC_CONNECTION_DATABASE_NAME("jdbc.connectionDatabaseName"),
	JDBC_CONNECTION_PASSWORD("jdbc.connectionPassword"),
	JDBC_CONNECTION_PORT_NUMBER("jdbc.connectionPortNumber"),
	JDBC_CONNECTION_SERVER_NAME("jdbc.connectionServerName"),
	JDBC_CONNECTION_URL("jdbc.connectionUrl"),
	JDBC_CONNECTION_USER_NAME("jdbc.connectionUserName"),
	JDBC_DATA_SOURCE_CLASS_NAME("jdbc.dataSourceClassName"),
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
