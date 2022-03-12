package com.rideboard.data.conf;

import java.io.FileInputStream;
import java.sql.DatabaseMetaData;
import java.util.Properties;

//import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

//@Configuration
public class PersistenceDataSourceConfig {
	private static Logger logger = LoggerFactory.getLogger(PersistenceDataSourceConfig.class);
	private Properties properties = null;
	private OracleDataSource ods = null;

	public PersistenceDataSourceConfig() {
		try {
			properties = new Properties();
			String file = System.getProperty("jboss.server.config.dir") + "/persistenceConfig.properties"; // change the slash
																								// according to your OS
//			try (FileInputStream fis = new FileInputStream(file)) {
//				properties.load(fis);
//			}
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			System.out.println();
			for(String key: properties.stringPropertyNames()) {
				System.out.println("prop " + key + " = " + properties.getProperty(key));
			}
			System.out.println();
			
			getDataSource();
			Thread.sleep(1000);
			getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OracleConnection getConnection() throws Exception {
		OracleConnection connection = null;
		logger.info("[getConnection] start");
		
		if (ods != null) {
			connection = (OracleConnection) ods.getConnection();
			System.out.println("Connection " + connection);
			
			if (connection != null) {
				// Get the JDBC driver name and version
				DatabaseMetaData dbmd = connection.getMetaData();
				System.out.println("Driver Name: " + dbmd.getDriverName());
				System.out.println("Driver Version: " + dbmd.getDriverVersion());
				// Print some connection properties
				System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
				System.out.println("Database Username is: " + connection.getUserName());
				System.out.println();
			}
		}
		return connection;
	}

//    @Bean
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
		System.out.println("ds : " + ods);
		
		return ods;
	}
}
