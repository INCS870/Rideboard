package com.rideboard.data.conf;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceDataSourceConfig extends org.springframework.jdbc.datasource.DriverManagerDataSource {
	private static Logger logger = LoggerFactory.getLogger(PersistenceDataSourceConfig.class);
	private Properties properties = null;

	public PersistenceDataSourceConfig() {
		try {
			properties = new Properties();
			String file = System.getProperty("jboss.server.config.dir") + "/persistenceConfig.properties"; // change the slash
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			for(String key: properties.stringPropertyNames()) {
				logger.debug("prop " + key + " = " + properties.getProperty(key));
			}
			this.setUrl(properties.getProperty("url"));
			this.setUsername(properties.getProperty("username"));
			this.setPassword(properties.getProperty("password"));
			this.setDriverClassName(properties.getProperty("driverClassName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
