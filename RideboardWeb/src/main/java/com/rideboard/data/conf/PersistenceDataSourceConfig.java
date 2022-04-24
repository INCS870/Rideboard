package com.rideboard.data.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceDataSourceConfig extends org.springframework.jdbc.datasource.DriverManagerDataSource {
	private static Logger logger = LoggerFactory.getLogger(PersistenceDataSourceConfig.class);
	private Properties properties = null;

	public PersistenceDataSourceConfig() {
		FileInputStream fis = null;
		try {
			properties = new Properties();
			String file = System.getProperty("jboss.server.config.dir") + "/persistenceConfig.properties"; // change the slash
			fis = new FileInputStream(file);
			properties.load(fis);
			for(String key: properties.stringPropertyNames()) {
				logger.info("prop " + key + " = " + properties.getProperty(key));
			}
			this.setUrl(properties.getProperty("url"));
			this.setUsername(properties.getProperty("username"));
			this.setPassword(com.rideboard.common.CryptoUtil.decrypt(properties.getProperty("password")));
			this.setDriverClassName(properties.getProperty("driverClassName"));
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}
}
