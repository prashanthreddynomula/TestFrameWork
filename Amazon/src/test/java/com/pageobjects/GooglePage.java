package com.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.supportlibrary.GenericFunctions;

public class GooglePage extends GenericFunctions {

	By SearchTextBox= By.name("q");
    By amazonLink=By.partialLinkText("www.amazon.co.");
    By numofPagesObject=By.xpath("//table[@id='nav']//td//a");
    By amazonLinkToOpen=By.xpath("//a[contains(@href,'www.amazon.co')]");
    
	public void searchData(String text) throws IOException {


		driver.findElement(SearchTextBox).sendKeys(text);
		driver.findElement(SearchTextBox).sendKeys(Keys.RETURN);
		// driver.findElement(By.xpath("//*[@value='Google Search']")).click();

	}

	public void navigatPages(String filename) throws IOException{
        List<WebElement> numofpages=driver.findElements(numofPagesObject);
        System.out.println(numofpages);
		if(isElementPresent(amazonLink)){
			click(amazonLink);	   
		}
		else{

			for(int i =2;i<=numofpages.size();i++){

				click(By.linkText(i+""));

				if(isElementPresent(amazonLinkToOpen)){
					click(amazonLinkToOpen);
					takeScreenshot(filename+genearteUniquieID());

					break;
				}
			}  

		}
	}
	


}



