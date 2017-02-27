package me.derekmahar.example.spring.configuration;

public class DerbyHibernateProperties extends BasicHibernateProperties implements HibernateProperties {

	public DerbyHibernateProperties(String jdbcPassword, String jdbcUserName) {
		super.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		super.put("hibernate.format_sql", "true");
		super.put("javax.persistence.jdbc.password", jdbcPassword);
		super.put("javax.persistence.jdbc.user", jdbcUserName);
		super.put("javax.persistence.schema-generation.create-database-schemas", "true");
		super.put("javax.persistence.schema-generation.database.action", "create");
	}

}
