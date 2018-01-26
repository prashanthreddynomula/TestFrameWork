package com.pageobjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.sun.istack.internal.logging.Logger;
import com.supportlibrary.GenericFunctions;

public class IphoneSearch extends GenericFunctions{
	
	final static Logger logger = Logger.getLogger(IphoneSearch.class);
	
	GooglePage googlePage=new GooglePage();
	AmazonPage amazonPage =new AmazonPage();
	
  @Test(dataProvider = "readData")
  public void amazonTest(int n ,String phonemodel) throws IOException {
	  
	  googlePage.searchData(phonemodel);
	  googlePage.navigatPages(phonemodel);
	  amazonPage.setSpecifications(phonemodel);
	  amazonPage.getSpecifications();
	  amazonPage.validatePhoneColors();
	  amazonPage.verifyPrice();
  }

  @DataProvider
  public Object[][] readData() {
    return new Object[][] {
      new Object[] { 1, "iphone" },
      new Object[] { 2, "samsung" },
    };
  }
  @BeforeMethod
  public void beforeTest() {
	  String driverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Driverfiles\\chromedriver.exe";
	  System.out.println(driverPath);
	  System.setProperty("webdriver.chrome.driver", driverPath);
	  driver= new ChromeDriver();
	  driver.get("http://www.google.com");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterMethod
  public void afterTest() {
	  driver.quit();
  }

}
