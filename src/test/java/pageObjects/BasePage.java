package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickelement(WebElement ele) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", ele);
	}


}
