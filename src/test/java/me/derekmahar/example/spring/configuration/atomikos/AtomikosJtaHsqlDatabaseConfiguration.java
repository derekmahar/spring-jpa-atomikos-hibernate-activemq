package me.derekmahar.example.spring.configuration.atomikos;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import me.derekmahar.example.spring.configuration.ApplicationEnvironment;
import me.derekmahar.example.spring.configuration.HibernateProperties;
import me.derekmahar.example.spring.configuration.HsqlHibernateProperties;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import org.hsqldb.jdbc.pool.JDBCXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtomikosJtaHsqlDatabaseConfiguration {

	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() throws SQLException {
		final AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setPoolSize(10);
		dataSource.setUniqueResourceName("xads");
		dataSource.setXaDataSource(getXaDataSource());
		dataSource.setXaProperties(getXaProperties());
		return dataSource;
	}

	private XADataSource getXaDataSource() throws SQLException {
		final JDBCXADataSource dataSource = new JDBCXADataSource();
		dataSource.setPassword(this.applicationEnvironment.getJdbcConnectionPassword());
		dataSource.setUrl(this.applicationEnvironment.getJdbcConnectionUrl());
		dataSource.setUser(this.applicationEnvironment.getJdbcConnectionUserName());
		return dataSource;
	}
	
	private Properties getXaProperties() {
		final Properties properties = new Properties();
		properties.setProperty("user", this.applicationEnvironment.getJdbcConnectionUserName());
		properties.setProperty("password", this.applicationEnvironment.getJdbcConnectionPassword());
		properties.setProperty("url", this.applicationEnvironment.getJdbcConnectionUrl());
		return properties;
	}

	@Bean
	public HibernateProperties hibernateProperties() {
		return new AtomikosJtaHibernateProperties(new HsqlHibernateProperties(this.applicationEnvironment.getJdbcConnectionUserName(),
				this.applicationEnvironment.getJdbcConnectionPassword()));
	}

}
