package me.derekmahar.example.spring.configuration.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import me.derekmahar.example.spring.configuration.ApplicationEnvironment;
import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
public class AtomikosJtaTransactionManagerConfiguration {

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean
	public ConnectionFactory connectionFactory() {
		final AtomikosConnectionFactoryBean atomikosConnectionFactory = new AtomikosConnectionFactoryBean();
		atomikosConnectionFactory.setPoolSize(10);
		atomikosConnectionFactory.setUniqueResourceName("xamq");
		atomikosConnectionFactory.setLocalTransactionMode(false);
		atomikosConnectionFactory.setXaConnectionFactory(xaConnectionFactory());
		final CachingConnectionFactory cachingConnectionFactory
				= new CachingConnectionFactory(atomikosConnectionFactory);
		cachingConnectionFactory.setSessionCacheSize(3);
		return cachingConnectionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(TransactionManager userTransactionManager,
			UserTransaction userTransaction) throws Throwable {
		return new JtaTransactionManager(userTransaction, userTransactionManager);
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public TransactionManager userTransactionManager() throws Throwable {
		final UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		AtomikosJtaPlatform.setTransactionManager(userTransactionManager);
		return userTransactionManager;
	}

	@Bean
	public UserTransaction userTransaction() throws Throwable {
		final UserTransactionImp userTransaction = new UserTransactionImp();
		userTransaction.setTransactionTimeout(1000);
		AtomikosJtaPlatform.setUserTransaction(userTransaction);
		return userTransaction;
	}

	private XAConnectionFactory xaConnectionFactory() {
		final ActiveMQXAConnectionFactory xaConnectionFactory = new ActiveMQXAConnectionFactory();
		xaConnectionFactory.setBrokerURL(this.applicationEnvironment.getJmsBrokerUrl());
		xaConnectionFactory.setPassword(this.applicationEnvironment.getJmsBrokerPassword());
		xaConnectionFactory.setTrustAllPackages(true);
		xaConnectionFactory.setUserName(this.applicationEnvironment.getJmsBrokerUserName());
		return xaConnectionFactory;
	}
}
