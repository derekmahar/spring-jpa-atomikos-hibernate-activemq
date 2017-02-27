package me.derekmahar.example.spring.configuration;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class BasicHibernateProperties implements HibernateProperties {

	protected final Properties hibernateProperties;

	public BasicHibernateProperties() {
		this.hibernateProperties = new Properties();
	}

	public BasicHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

	@Override
	public Properties getProperties() {
		return this.hibernateProperties;
	}

	public void put(String key, String value) {
		this.hibernateProperties.setProperty(key, value);
	}

	@Override
	public void putAll(HibernateProperties properties) {
		for (final Entry<Object, Object> property : properties.getProperties().entrySet())
			this.hibernateProperties.put(property.getKey(), property.getValue());
	}

	@Override
	public void putAll(Map<String, String> properties) {
		for (final Entry<String, String> property : properties.entrySet())
			this.hibernateProperties.put(property.getKey(), property.getValue());
	}

	@Override
	public void putAll(Properties properties) {
		for (final Entry<Object, Object> property : properties.entrySet())
			this.hibernateProperties.put(property.getKey(), property.getValue());
	}

	@Override
	public void setProperty(String key, String value) {
		this.hibernateProperties.setProperty(key, value);
	}
	
}
