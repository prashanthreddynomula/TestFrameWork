package com.supportlibrary;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.enums.BrowserType;


public class PropertiesReader {	
	private Properties properties;
	private final String propertyFilePath= "src\\test\\resources\\ConfigurationSettings.properties";

	public PropertiesReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public String getDriverPath(){
		String driverPath = properties.getProperty(properties.getProperty("browser")+"_"+"driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the ConfigurationSettings.properties file for the Key:driverPath:Please check browsername");		
	}

	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}

	

	public BrowserType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome"))
			return BrowserType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) 
			return BrowserType.FIREFOX;
		else if(browserName.equals("iexplorer")) 
			return BrowserType.INTERNETEXPLORER;
		else if(browserName.equals("edge")) 
			return BrowserType.EDGE;
		else if(browserName.equals("opera"))
			return BrowserType.OPERA;
		else if(browserName.equals("phantom"))
			return BrowserType.PHANTOMJS;
		else throw new RuntimeException("Browser Name is not matched : " + browserName);
	}




}