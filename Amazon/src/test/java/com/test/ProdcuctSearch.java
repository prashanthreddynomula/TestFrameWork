package com.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.AmazonPage;
import com.pageobjects.GooglePage;
import com.supportlibrary.ExcelAccess;
import com.supportlibrary.GenericFunctions;
import com.supportlibrary.WebdriverUtil;

/**
 * @author Shankar
 *
 * ${tags}
 */

public class ProdcuctSearch extends GenericFunctions{

	
	final static Logger logger = Logger.getLogger(ProdcuctSearch.class);

	GooglePage googlePage;
	AmazonPage amazonPage;
	private final String TestDataFilePath= System.getProperty("user.dir")+"\\src\\test\\resources\\Datatable\\TestData.xls";
	/**
	 * 
	 */
	@BeforeMethod
	public void beforeTest() {
		
		WebdriverUtil webDriverUtil= new WebdriverUtil();
		driver = webDriverUtil.getDriver();
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
	
	public void amazonTest(String Scenario ,String product) throws IOException {

		googlePage.searchForData(product);
		googlePage.navigatePages(product);
		amazonPage.RetrieveSpecifications(product);		
		amazonPage.validateProductColors();
		amazonPage.verifyPrice();
	}



	/**
	 * 
	 */
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
		
     String[][] testObjArray = null;
		
		try {
			testObjArray=ExcelAccess.getCellData(TestDataFilePath,"ProductSearch");
			 
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testObjArray;
		/*return new Object[][] {
				new Object[] { 1, "iphone" },
				new Object[] { 2, "samsung" },
		};*/
	}
}
