package me.derekmahar.example.spring.configuration;

import java.util.Map;
import java.util.Properties;

public interface HibernateProperties {
	Properties getProperties();
	void put(String key, String value);
	void putAll(HibernateProperties properties);
	void putAll(Map<String, String> properties);
	void putAll(Properties properties);
	void setProperty(String key, String value);
}
