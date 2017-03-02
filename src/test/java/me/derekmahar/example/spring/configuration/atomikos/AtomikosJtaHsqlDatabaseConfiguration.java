package me.derekmahar.example.spring.configuration.atomikos;

import me.derekmahar.example.spring.configuration.ApplicationEnvironment;
import me.derekmahar.example.spring.configuration.HibernateProperties;
import me.derekmahar.example.spring.configuration.HsqlHibernateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtomikosJtaHsqlDatabaseConfiguration {

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean
	public HibernateProperties hibernateProperties() {
		return new AtomikosJtaHibernateProperties(new HsqlHibernateProperties(this.applicationEnvironment.getJdbcConnectionUserName(),
				this.applicationEnvironment.getJdbcConnectionPassword()));
	}

}
