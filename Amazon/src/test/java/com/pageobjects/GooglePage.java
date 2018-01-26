package com.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.sun.istack.internal.logging.Logger;
import com.supportlibrary.GenericFunctions;

public class GooglePage extends GenericFunctions {
	final static Logger logger = Logger.getLogger(GooglePage.class);
	By SearchTextBox= By.name("q");
    By amazonLink=By.partialLinkText("www.amazon.co.");
    By numofPagesObject=By.xpath("//table[@id='nav']//td//a");
    By amazonLinkToOpen=By.xpath("//a[contains(@href,'www.amazon.co')]");
    
	public void searchData(String text) throws IOException {


		driver.findElement(SearchTextBox).sendKeys(text);
		logger.info("Searching for" +text +"in Google page");
		driver.findElement(SearchTextBox).sendKeys(Keys.RETURN);
		// driver.findElement(By.xpath("//*[@value='Google Search']")).click();

	}

	public void navigatPages(String filename) throws IOException{
        List<WebElement> numofpages=driver.findElements(numofPagesObject);
        logger.info("Number of pages avaialable : " +numofpages);
       
		if(isElementPresent(amazonLink)){
			click(amazonLink);
			logger.info("Clicked on Amazon link");
		}
		else{

			for(int i =2;i<=numofpages.size();i++){

				click(By.linkText(i+""));

				if(isElementPresent(amazonLinkToOpen)){
					click(amazonLinkToOpen);
					logger.info("Link found in page : "+ i );
					takeScreenshot(filename+genearteUniquieID());

					break;
				}
			}  

		}
	}
	


}



