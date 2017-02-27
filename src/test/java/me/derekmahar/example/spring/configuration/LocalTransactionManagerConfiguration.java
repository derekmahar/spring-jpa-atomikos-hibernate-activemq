package me.derekmahar.example.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfiguration.class, HsqlDatabaseConfiguration.class, JpaConfiguration.class,
	JpaTransactionManagerConfiguration.class, MessageBrokerConfiguration.class, ServiceConfiguration.class})
public class LocalTransactionManagerConfiguration {
}
