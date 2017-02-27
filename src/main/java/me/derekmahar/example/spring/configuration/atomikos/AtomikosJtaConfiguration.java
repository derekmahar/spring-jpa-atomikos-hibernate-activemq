package me.derekmahar.example.spring.configuration.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import me.derekmahar.example.spring.configuration.ApplicationEnvironment;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class AtomikosJtaConfiguration {

	@Autowired
	private ApplicationEnvironment applicationProperties;

	@Bean
	public UserTransaction userTransaction() throws Throwable {
		final UserTransactionImp userTransaction = new UserTransactionImp();
		userTransaction.setTransactionTimeout(1000);
		return userTransaction;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public TransactionManager transactionManager() throws Throwable {
		final UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager(TransactionManager transactionManager,
			UserTransaction userTransaction) throws Throwable {
		return new JtaTransactionManager(userTransaction, transactionManager);
	}

	@Bean
	public XAConnectionFactory xaConnectionFactory(RedeliveryPolicy redeliveryPolicy) {
		final ActiveMQXAConnectionFactory xaConnectionFactory = new ActiveMQXAConnectionFactory();
		xaConnectionFactory.setBrokerURL(this.applicationProperties.getJmsBrokerUrl());
		xaConnectionFactory.setPassword(this.applicationProperties.getJmsBrokerPassword());
		xaConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
		xaConnectionFactory.setTrustAllPackages(true);
		xaConnectionFactory.setUserName(this.applicationProperties.getJmsBrokerUserName());
		return xaConnectionFactory;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public ConnectionFactory connectionFactory(XAConnectionFactory xaConnectionFactory) {
		final AtomikosConnectionFactoryBean atomikosConnectionFactory = new AtomikosConnectionFactoryBean();
		atomikosConnectionFactory.setUniqueResourceName("xamq");
		atomikosConnectionFactory.setLocalTransactionMode(false);
		atomikosConnectionFactory.setXaConnectionFactory(xaConnectionFactory);

		final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setMaxConnections(3);
		pooledConnectionFactory.setConnectionFactory(new CachingConnectionFactory(atomikosConnectionFactory));
		return atomikosConnectionFactory;
	}
}
