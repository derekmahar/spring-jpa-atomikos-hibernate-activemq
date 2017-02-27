package me.derekmahar.example.spring.configuration;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.usage.MemoryUsage;
import org.apache.activemq.usage.SystemUsage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

	public static final String BROKER_NAME = "test";
	
	@Bean
	public static BrokerService brokerService() throws Exception {
		final BrokerService broker = new BrokerService();
		broker.setBrokerName(BROKER_NAME);
		broker.setDataDirectory("target/activemq-data");
		broker.setUseJmx(false);
		broker.setUseShutdownHook(false);
		broker.setPersistent(false);
		broker.setSystemUsage(createMemoryManager(10));
		return broker;
	}
	
	private static SystemUsage createMemoryManager(int percentOfJvmHeap) {
		final SystemUsage memoryManager = new SystemUsage();
        final MemoryUsage memoryLimit = new MemoryUsage();
		memoryLimit.setPercentOfJvmHeap(percentOfJvmHeap);
		return memoryManager;
	}

}
