package me.derekmahar.example.spring.configuration;

import me.derekmahar.example.spring.messenger.PersonMessenger;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Import({CommonConfiguration.class})
public class MessagingConfiguration {

	public static final String PERSON_QUEUE = "person.queue";

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean
	public ConnectionFactory jmsConnectionFactory() {
		final ActiveMQConnectionFactory activeMqConnectionFactory = new ActiveMQConnectionFactory(
				this.applicationEnvironment.getJmsBrokerUrl());
		activeMqConnectionFactory.setTrustAllPackages(true);
		return new CachingConnectionFactory(activeMqConnectionFactory);
	}

	@Bean
	public JmsOperations jmsTemplate(ConnectionFactory connectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestinationName(PERSON_QUEUE);
		return jmsTemplate;
	}
	
	@Bean
	public PersonMessenger personMessenger(JmsOperations jmsOperations) {
		return new PersonMessenger(jmsOperations);
	}
	
	@Bean(name = PERSON_QUEUE)
	public Queue personQueue() {
		return new ActiveMQQueue(PERSON_QUEUE);
	}
}
