package me.derekmahar.example.spring.configuration;

import me.derekmahar.example.spring.messenger.PersonMessenger;
import me.derekmahar.example.spring.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MessagingConfiguration.class})
public class ServiceConfiguration {
	@Bean
	public PersonService personService(PersonMessenger personMessenger) {
		return new PersonService(personMessenger);
	}
}
