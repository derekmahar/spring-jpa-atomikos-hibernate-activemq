package me.derekmahar.example.spring.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class HsqlDatabaseConfiguration {

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.HSQL)
				//.addScript("createSchemaHsql.sql")
				.build();
	}

	@Bean
	public HibernateProperties hibernateProperties() {
		return new HsqlHibernateProperties(this.applicationEnvironment.getJdbcConnectionUserName(),
				this.applicationEnvironment.getJdbcConnectionPassword());
	}
}
