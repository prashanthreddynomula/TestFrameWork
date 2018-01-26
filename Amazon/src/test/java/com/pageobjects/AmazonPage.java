package com.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.supportlibrary.GenericFunctions;

public class AmazonPage extends GenericFunctions{
	
	By productTitle =By.id("productTitle");
	By productPrice= By.xpath("//span[contains(text(),'Price')]/../following-sibling::td[1]/span");
	By cell_Technology= By.xpath("//span[contains(text(),'Technology')]/../following-sibling::td[1]/span");
	By display_Size=By.xpath("//span[contains(text(),'Display')]/../following-sibling::td[1]/span");
	By dimensions=By.xpath("//span[contains(text(),'Dimensions')]/../following-sibling::td[1]/span");
	By weight=By.xpath("//span[contains(text(),'Weight')]/../following-sibling::td[1]/span");
	By memory_Storage=By.xpath("//span[contains(text(),'Memory')]/../following-sibling::td[1]/span");
	By operating_System=By.xpath("//span[contains(text(),'Operating System')]/../following-sibling::td[1]/span");
	By multicolor_Phone=By.xpath("//img[@class='imgSwatch']");
	By single_color_Phone=By.xpath("//label[contains(text(),'Colour Name')]/following-sibling::span");
	
	Map<String,String> Specifications=new HashMap<String, String>();
	
	public void setSpecifications(String model){
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

		}
	}
	public void getSpecifications()
	{
		Set<String> keys = Specifications.keySet();
		Iterator<String> itr = keys.iterator();

		String key;
		String value;
		while(itr.hasNext())
		{
			key = (String)itr.next();
			value = (String)Specifications.get(key);
			System.out.println(key + " - "+ value);
		}
	}
	
	public void verifyPrice(){
		
		String priceValue=getText(productPrice).replaceAll("[^0-9.]", "");
		Double value=Double.parseDouble(priceValue);
		assertThat(value,is(lessThan(1000.00)));
		System.out.println(priceValue);
		
	}
	
	public void validatePhoneColors(){
		
		if(isElementPresent(productTitle)){
		   List<WebElement> multiColor= driver.findElements(multicolor_Phone);
		   System.out.println("Number of different colors available with "+productTitle+ " "+ multiColor.size());
		   for(int i=0;i<=multiColor.size()-1;i++){

			   multiColor.get(i).click();
			   String selectedColor=driver.findElement(single_color_Phone).getText();
			   System.out.println("color selected is "+selectedColor);
		   }
		}
		
	}
}
