package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("prod")
@EnableTransactionManagement
public class JPAProductionConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL93Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");

		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));
		// dataSource.setUrl("jdbc:postgresql://" + dbUrl.getHost() + ":" +
		// dbUrl.getPort() + dbUrl.getPath());
		// dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
		// dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);
		dataSource.setUrl(
				"postgres://oikyqqvvxltytw:ad9a8c9da567ac35ea89b8d069d5187e7b616e2062eab9bbc4b7702aeea570e9@ec2-23-21-86-22.compute-1.amazonaws.com:5432/def50oq4e91puf");
		dataSource.setUsername("oikyqqvvxltytw");
		dataSource.setPassword("ad9a8c9da567ac35ea89b8d069d5187e7b616e2062eab9bbc4b7702aeea570e9");

		return dataSource;
	}
}