package com.supportlibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.pageobjects.AmazonPage;
import com.sun.istack.internal.logging.Logger;
import com.test.ProdcuctSearch;

/**
 * @author Shankar
 *
 */
public class GenericFunctions {
	final static Logger logger = Logger.getLogger(GenericFunctions.class);
	public static WebDriver driver;
	public boolean isElementPresent(WebElement element){
		
		try{
			element.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
		
		
	}
	/**
	 * @param element
	 */
	public void waitElementPresent(WebElement element){
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void scrollToElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	/**
	 * @param element
	 */
	public void click(WebElement element){
		if(isElementPresent(element)){
			element.click();			
			logger.info("successfully clicked");
		}
		else{

			logger.info("Element is not available to click");
		}
		
		
	}
	
	/**
	 * @param element
	 * @return
	 */
	public String getText(WebElement element){
		String value=null;
		
		if(isElementPresent(element)){
			value=element.getText();
			
		}
		else{
			
			logger.info("Element is not available to get the text");
		}
		return value;
		
		
	}
	
	/**
	 * @param name
	 * @throws IOException
	 */
	public void takeScreenshot(String name) throws IOException{
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\screenshot"+name+".png"));
	}
	/**
	 * @return unique string
	 */
	/**
	 * @return
	 */
	public String genearteUniquieID(){
		Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime;
        
	}

}
