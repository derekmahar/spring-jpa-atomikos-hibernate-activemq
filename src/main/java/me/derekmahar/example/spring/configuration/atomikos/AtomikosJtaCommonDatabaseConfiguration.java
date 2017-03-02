package me.derekmahar.example.spring.configuration.atomikos;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import me.derekmahar.example.spring.configuration.ApplicationEnvironment;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtomikosJtaCommonDatabaseConfiguration {

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() throws SQLException {
		final AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setPoolSize(10);
		dataSource.setUniqueResourceName("xads");
		dataSource.setXaDataSourceClassName(this.applicationEnvironment.getJdbcDataSourceClassName());
		dataSource.setXaProperties(getXaProperties());
		return dataSource;
	}

	private Properties getXaProperties() {
		final Properties properties = new Properties();
		properties.setProperty("user", this.applicationEnvironment.getJdbcConnectionUserName());
		properties.setProperty("password", this.applicationEnvironment.getJdbcConnectionPassword());
		properties.setProperty("url", this.applicationEnvironment.getJdbcConnectionUrl());
		return properties;
	}

}
