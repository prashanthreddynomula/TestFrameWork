package com.supportlibrary;

import java.lang.ref.PhantomReference;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.enums.BrowserType;

/**
 * @author shank
 *
 */

public class WebdriverUtil {
    
	
	private WebDriver driver;
	private static BrowserType driverType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String OPERA_DRIVER_PROPERTY = "webdriver.opera.driver";
	private static final String PHANTOM_DRIVER_PROPERTY = "phantomjs.binary.path";
	private static final String  EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";
	
	
	
	/**
	 * 
	 */
	public WebdriverUtil() {
		driverType = FileReader.getInstance().getPropertiesReader().getBrowser();
			}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}




	/**
	 * @return
	 */
	private WebDriver createDriver() {
		
		PropertiesReader properties=FileReader.getInstance().getPropertiesReader();
		String driverPath= System.getProperty("user.dir")+properties.getDriverPath();
        switch (driverType) {	    
        case FIREFOX : 
        	System.setProperty(FIREFOX_DRIVER_PROPERTY, driverPath);
        	driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, driverPath);
        	driver = new ChromeDriver();
    		break;
        case INTERNETEXPLORER : 
        	System.setProperty(IE_DRIVER_PROPERTY,driverPath);
        	driver = new InternetExplorerDriver();
    		break;
        case EDGE : 
        	System.setProperty(EDGE_DRIVER_PROPERTY,driverPath);
        	driver = new EdgeDriver();
    		break;
        case OPERA : 
        	System.setProperty(OPERA_DRIVER_PROPERTY,driverPath);
        	driver = new OperaDriver();
    		break;
        case PHANTOMJS : 
        	System.setProperty(PHANTOM_DRIVER_PROPERTY,driverPath);
        	driver = new PhantomJSDriver();
    		break;
        
		default:
			throw new RuntimeException("Browser is not implemented");
        }

        driver.manage().timeouts().implicitlyWait(FileReader.getInstance().getPropertiesReader().getImplicitlyWait(), TimeUnit.SECONDS);
        
		return driver;
	}	

	/**
	 * 
	 */
	public void closeDriver() {
		driver.close();
		
	}
}
