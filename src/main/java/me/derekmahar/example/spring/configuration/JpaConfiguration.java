package me.derekmahar.example.spring.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "me.derekmahar.example.spring.repository")
public class JpaConfiguration {

	@Autowired
	private HibernateProperties hibernateProperties;

	@Bean
	public AbstractEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactory.setPackagesToScan("me.derekmahar.example.spring.model");
		entityManagerFactory.setJpaProperties(this.hibernateProperties.getProperties());
		return entityManagerFactory;
	}

	@Bean
	public JpaVendorAdapter hibernateJpaVendorAdapter() {
		final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		jpaAdapter.setShowSql(true);
		jpaAdapter.setGenerateDdl(true);
		return jpaAdapter;
	}

}
