package me.derekmahar.example.spring.configuration;

public interface ApplicationPropertiesSource {
	String getJdbcConnectionDatabaseName();
	String getJdbcConnectionPassword();
	int getJdbcConnectionPortNumber();
	String getJdbcConnectionServerName();
	String getJdbcConnectionUrl();
	String getJdbcConnectionUserName();
	String getJdbcDataSourceClassName();
	String getJmsBrokerPassword();
	String getJmsBrokerUrl();
	String getJmsBrokerUserName();
}
