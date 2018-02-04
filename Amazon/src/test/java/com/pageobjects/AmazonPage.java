package com.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import com.supportlibrary.GenericFunctions;

/**
 * @author Shankar
 *
 */
public class AmazonPage extends GenericFunctions{
	
	final static Logger logger = Logger.getLogger(AmazonPage.class);
	final WebDriver driver;
	
	@FindBy(how = How.ID, using = "productTitle")
    private WebElement productTitle;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Price')]/../following-sibling::td[1]/span")
    private WebElement productPrice;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Technology')]/../following-sibling::td[1]/span")
    private WebElement cell_Technology;
	
	@FindBy(how = How.XPATH, using = 
			"//span[contains(text(),'Display')]/../following-sibling::td[1]/span")
    private WebElement display_Size;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Dimensions')]/../following-sibling::td[1]/span")
    private WebElement dimensions;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Weight')]/../following-sibling::td[1]/span")
    private WebElement weight;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Memory')]/../following-sibling::td[1]/span")
    private WebElement memory_Storage;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Operating System')]/../following-sibling::td[1]/span")
    private WebElement operating_System;	
	
	@FindBys( value = { @FindBy(how = How.XPATH, using = "//img[@class='imgSwatch']")})
    private List<WebElement> multicolor_Phone;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Colour Name')]/following-sibling::span")
    private WebElement single_color_Phone;
	
	

	
	Map<String,String> Specifications=new HashMap<String, String>();
	
	/**
	 * @param driver
	 */
	public AmazonPage(WebDriver driver)
	 
	{
 
		this.driver = driver;
 
		}
	/**
	 * @param model
	 */
	public void RetrieveSpecifications(String model){
		if(isElementPresent(productTitle)){
			String prodcutName=driver.findElement(By.id("productTitle")).getText();
			if(prodcutName.toLowerCase().contains(model)){
				Specifications.put("Price", getText(productPrice));
				Specifications.put("Cell_Technology", getText(cell_Technology));
				Specifications.put("Display_Size", getText(display_Size));
				Specifications.put("Dimensions", getText(dimensions));
				Specifications.put("Weight", getText(weight));
				Specifications.put("Memory_Storage ", getText(memory_Storage));
				Specifications.put("Operating_System", getText(operating_System));				
			}
			
			getSpecifications();

		}
	}
	/**
	 * 
	 */
	public void getSpecifications()
	{
		Set<String> keys = Specifications.keySet();
		Iterator<String> itr = keys.iterator();

		String key;
		String value;
		logger.info("Specifications: " );
		while(itr.hasNext())
		{
			key = (String)itr.next();
			value = (String)Specifications.get(key);				
			logger.info(key + " : "+ value );
		}
	}
	
	/**
	 * 
	 */
	public void verifyPrice(){
		
		String priceValue=getText(productPrice).replaceAll("[^0-9.]", "");
		Double value=Double.parseDouble(priceValue);
		assertThat(value,is(lessThan(1000.00)));
		logger.info("Price value is: "+ priceValue );
		
	}
	
	/**
	 * 
	 */
	public void validateProductColors(){
		
		if(isElementPresent(productTitle)){
		   //List<WebElement> multiColor= driver.findElements(multicolor_Phone);
		   logger.info("Number of different colors available with "+productTitle+ " are "+ multicolor_Phone.size());
		   
		   for(int i=0;i<=multicolor_Phone.size()-1;i++){

			   multicolor_Phone.get(i).click();
			   String selectedColor=single_color_Phone.getText();
			   logger.info("color selected is "+selectedColor );
			  
		   }
		}
		
	}
}
