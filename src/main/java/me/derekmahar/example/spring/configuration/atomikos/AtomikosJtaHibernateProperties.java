package me.derekmahar.example.spring.configuration.atomikos;

import me.derekmahar.example.spring.configuration.BasicHibernateProperties;
import me.derekmahar.example.spring.configuration.HibernateProperties;

public class AtomikosJtaHibernateProperties extends BasicHibernateProperties implements HibernateProperties {

	public AtomikosJtaHibernateProperties(HibernateProperties defaultHibernateProperties) {
		super.putAll(defaultHibernateProperties);
		super.put("hibernate.transaction.jta.platform",
				AtomikosJtaPlatform.class.getName());
		super.put("javax.persistence.transactionType", "JTA");
		super.put("hibernate.transaction.factory_class",
				"org.hibernate.transaction.JTATransactionFactory");
		super.put("hibernate.transaction.manager_lookup_class",
				"com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup");
	}

}
