package com.rideboard.data.conf;

import java.io.FileInputStream;
import java.sql.DatabaseMetaData;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class PersistenceDataSourceConfig {
	private static Logger logger = LoggerFactory.getLogger(PersistenceDataSourceConfig.class);
	private Properties properties = null;
	private OracleDataSource ods = null;

	public PersistenceDataSourceConfig() {
		try {
			properties = new Properties();
			String file = System.getProperty("jboss.server.config.dir") + "/persistenceConfig.properties"; // change the slash
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			for(String key: properties.stringPropertyNames()) {
				logger.info("prop " + key + " = " + properties.getProperty(key));
			}			
			getDataSource();
			Thread.sleep(1000);
			//getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OracleConnection getConnection() throws Exception {
		OracleConnection connection = null;
		logger.info("[getConnection] start");
		
		if (ods != null) {
			connection = (OracleConnection) ods.getConnection();
			logger.info("Connection " + connection);
			
			if (connection != null) {
				// Get the JDBC driver name and version
				DatabaseMetaData dbmd = connection.getMetaData();
				logger.info("Driver Name: " + dbmd.getDriverName());
				logger.info("Driver Version: " + dbmd.getDriverVersion());
				// Print some connection properties
				logger.info("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
				logger.info("Database Username is: " + connection.getUserName());
			}
		}
		return connection;
	}

	public OracleDataSource getDataSource() throws Exception {
		ods = new OracleDataSource();
		logger.info("[getDataSource] start");
		
		String url 		= properties.getProperty("url");
		String uname 	= properties.getProperty("user");
		String pwd 		= properties.getProperty("password");

		Properties info = new Properties();
		info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, uname);
		info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, pwd);
		info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
		
		ods.setURL(url);
		ods.setConnectionProperties(info);
		logger.info("ds : " + ods);
		
		return ods;
	}
}
