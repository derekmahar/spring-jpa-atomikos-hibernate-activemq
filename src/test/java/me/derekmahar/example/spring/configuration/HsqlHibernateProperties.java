package me.derekmahar.example.spring.configuration;

public class HsqlHibernateProperties extends BasicHibernateProperties implements HibernateProperties {

	public HsqlHibernateProperties(String password, String userName) {
		super.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		super.put("hibernate.format_sql", "true");
		super.put("javax.persistence.jdbc.user", userName);
		super.put("javax.persistence.jdbc.password", password);
		super.put("javax.persistence.schema-generation.create-database-schemas", "true");
		super.put("javax.persistence.schema-generation.create-script-source", "createSchemaHsql.sql");
		super.put("javax.persistence.schema-generation.create-source", "script-then-metadata");
		super.put("javax.persistence.schema-generation.database.action", "create");
	}

}
