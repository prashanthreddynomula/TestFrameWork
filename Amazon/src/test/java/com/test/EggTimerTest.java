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

import com.pageobjects.EggTimerPage;
import com.supportlibrary.ExcelAccess;
import com.supportlibrary.WebdriverUtil;

public class EggTimerTest {
	final static Logger logger = Logger.getLogger(EggTimerTest.class);

	EggTimerPage egggTimerPage;
	 WebDriver driver ;
	private final String TestDataFilePath= System.getProperty("user.dir")+"\\src\\test\\resources\\Datatable\\TestData.xls";

	/**
	 * 
	 */
	@BeforeMethod
	public void beforeMethod() {
		
		WebdriverUtil webDriverUtil= new WebdriverUtil();
		driver = webDriverUtil.getDriver();
		driver.get("http://e.ggtimer.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		egggTimerPage = PageFactory.initElements(driver, EggTimerPage.class);
		
	}
	
	/**
	 * @param Scenario
	 * @param time
	 * @throws IOException
	 */
	@Test(dataProvider = "testData")
	
	public void egggTimerTest(String Scenario ,String time) throws IOException {
    
		egggTimerPage.enterData(time);
		egggTimerPage.verifyUrl(time);
		egggTimerPage.verifyTimer(time);
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
	@DataProvider(name="testData")
	public Object[][] readData() {
		String[][] testObjArray = null;
		
		try {
			testObjArray=ExcelAccess.getCellData(TestDataFilePath,"EggTimer");
			 
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testObjArray;
		
	}
}
