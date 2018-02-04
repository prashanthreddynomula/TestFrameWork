package com.pageobjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.supportlibrary.GenericFunctions;

/**
 * @author shankar
 *
 */
public class EggTimerPage extends GenericFunctions {

	final static Logger logger = Logger.getLogger(EggTimerPage.class);

	final WebDriver driver;

	@FindBy(how = How.NAME, using = "start_a_timer")
	private WebElement SearchTextBox;


	@FindBy(how = How.ID, using = "timergo")
	private WebElement GoButton;


	@FindBy(how = How.ID, using = "progressText")
	private WebElement ProgressText;




	/**
	 * @param driver
	 */
	public EggTimerPage(WebDriver driver)

	{
		this.driver = driver;
	}


	/**
	 * @param text
	 * @throws IOException
	 */
	public void enterData(String text) {
		SearchTextBox.clear();
		SearchTextBox.sendKeys(text);
		logger.info("Start a timer with " +text );
		GoButton.click();

	}
	public void verifyUrl(String text) {

		String url_Expected="e.ggtimer.com/"+text;
		String url=driver.getCurrentUrl();
		if(url.equalsIgnoreCase(url_Expected)) {
			logger.info("Window title is as expected and the value is  " +url );

		}


	}

	/**
	 * @param text
	 */
	public void verifyTimer(String text) {
		String before=text;

		String after=null;
        if(!isElementPresent(ProgressText)) {
				logger.error("Please enter valid input ");
				
        }
		while(!isAlertPresent(driver)){

			if(isElementPresent(ProgressText)) {
				after=ProgressText.getText();
				if(!after.equals(before)){
					logger.info("Current value is  " +after );
					before=after;

				}
			}
			

		}
	}



}
