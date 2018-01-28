package com.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import com.sun.istack.internal.logging.Logger;
import com.supportlibrary.GenericFunctions;

/**
 * @author Shankar
 *
 */
public class GooglePage extends GenericFunctions {
	final static Logger logger = Logger.getLogger(GooglePage.class);
	
	final WebDriver driver;
	@FindBy(how = How.NAME, using = "q")
    private WebElement SearchTextBox;
	
	@FindBys( value = { @FindBy(how = How.XPATH, using = "//table[@id='nav']//td//a")})
    private List<WebElement> numofPagesObject;
	
	@FindBy(how = How.XPATH, using = "//a[contains(@href,'www.amazon.co')]")
    private WebElement amazonLinkToOpen;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "www.amazon.co.")
    private WebElement amazonLink;
	
	
	/**
	 * @param driver
	 */
	public GooglePage(WebDriver driver)
	 
	{
		this.driver = driver;
 	}
    

	/**
	 * @param text
	 * @throws IOException
	 */
	public void searchForData(String text) throws IOException {
	   SearchTextBox.sendKeys(text);
		logger.info("Searching for" +text +"in Google page");
		SearchTextBox.sendKeys(Keys.RETURN);
	}

	/**
	 * @param filename
	 * @throws IOException
	 */
	public void navigatePages(String filename) throws IOException{
        
        logger.info("Number of pages avaialable : " +numofPagesObject.size());
       
		if(isElementPresent(amazonLink)){
			click(amazonLink);
			logger.info("Clicked on Amazon link");
		}
		else{

			for(int i =2;i<=numofPagesObject.size();i++){

				click(numofPagesObject.get(i));

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



