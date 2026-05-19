package com.ernesto.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	Properties prop;
	
	public ConfigReader() {
		
		prop = new Properties();
		
		try {
			
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			
			prop.load(fis);
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String getBrowser() {
		
		return prop.getProperty("browser");
	}
	public String getURL() {
		
		return prop.getProperty("url");
	}
	
	public int getTimeout() {
		
		return Integer.parseInt(prop.getProperty("timeout"));
	}
	
	public boolean isHeadless() {
		
		return Boolean.parseBoolean(prop.getProperty("headless"));
	}
}

