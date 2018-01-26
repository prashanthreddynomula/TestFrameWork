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

public class GenericFunctions {
	public static WebDriver driver;
	public boolean isElementPresent(By ele ){
		
		try{
			driver.findElement(ele);
			return true;
		}
		catch(Exception e){
			return false;
		}
		
		
	}
	public void waitElementPresent(WebElement element){
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void scrollToElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public void click(By by){
		if(isElementPresent(by)){
			driver.findElement(by).click();
			System.out.println("successfully clicked");
		}
		else{
			System.out.println("Element is not available to clic");
		}
		
		
	}
	
	public String getText(By by){
		String value=null;
		
		if(isElementPresent(by)){
			value=driver.findElement(by).getText();
			
		}
		else{
			System.out.println("Element is not available to get the text");
		}
		return value;
		
		
	}
	
	public void takeScreenshot(String name) throws IOException{
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\screenshot"+name+".png"));
	}
	public String genearteUniquieID(){
		Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime;
        
	}

}
