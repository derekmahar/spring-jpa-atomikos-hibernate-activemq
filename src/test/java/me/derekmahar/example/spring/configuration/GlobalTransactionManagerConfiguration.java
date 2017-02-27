package me.derekmahar.example.spring.configuration;

import me.derekmahar.example.spring.configuration.atomikos.AtomikosJtaHsqlDatabaseConfiguration;
import me.derekmahar.example.spring.configuration.atomikos.AtomikosJtaTransactionManagerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AtomikosJtaHsqlDatabaseConfiguration.class, AtomikosJtaTransactionManagerConfiguration.class,
	CommonConfiguration.class, JpaConfiguration.class, MessageBrokerConfiguration.class, ServiceConfiguration.class,
	TransactionTemplateConfiguration.class})
@DependsOn({"brokerService"})
public class GlobalTransactionManagerConfiguration {
}
