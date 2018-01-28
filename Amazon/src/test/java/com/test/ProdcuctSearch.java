package com.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.pageobjects.AmazonPage;
import com.pageobjects.GooglePage;
import com.sun.istack.internal.logging.Logger;
import com.supportlibrary.GenericFunctions;

/**
 * @author Shankar
 *
 * ${tags}
 */

public class ProdcuctSearch extends GenericFunctions{

	final static Logger logger = Logger.getLogger(ProdcuctSearch.class);

	GooglePage googlePage;
	AmazonPage amazonPage;

	/**
	 * 
	 */
	@BeforeMethod
	public void beforeTest() {
		String driverPath=System.getProperty("user.dir")+"\\selenium\\bin\\windows\\googlechrome\\64bit\\chromedriver.exe";
		System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver= new ChromeDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		googlePage = PageFactory.initElements(driver, GooglePage.class);
		amazonPage =PageFactory.initElements(driver, AmazonPage.class);
	}
	/**
	 * @param n
	 * @param product
	 * @throws IOException
	 */
	@Test(dataProvider = "readData")
	
	public void amazonTest(int n ,String product) throws IOException {

		googlePage.searchForData(product);
		googlePage.navigatePages(product);
		amazonPage.RetrieveSpecifications(product);		
		amazonPage.validateProductColors();
		amazonPage.verifyPrice();
	}



	/**
	 * 
	 */
	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
	/**
	 * @return
	 */
	@DataProvider
	public Object[][] readData() {
		return new Object[][] {
				new Object[] { 1, "iphone" },
				new Object[] { 2, "samsung" },
		};
	}
}
