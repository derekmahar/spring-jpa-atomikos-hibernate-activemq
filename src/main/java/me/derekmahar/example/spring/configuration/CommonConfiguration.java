package me.derekmahar.example.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class CommonConfiguration {

	@Autowired
	private Environment applicationEnvironment;

	@Bean
	public ApplicationEnvironment applicationEnvironment() {
		return new ApplicationEnvironment(this.applicationEnvironment);
	}
}
