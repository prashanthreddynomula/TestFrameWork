package com.supportlibrary;


public class FileReader {
 
	private static FileReader fileReaderManager = new FileReader();
	private static PropertiesReader Properties;
 
	private FileReader() {
	}
 
	 public static FileReader getInstance( ) {
	      return fileReaderManager;
	 }
 
	 public PropertiesReader getPropertiesReader() {
		 return (Properties == null) ? new PropertiesReader() : Properties;
	 }
}