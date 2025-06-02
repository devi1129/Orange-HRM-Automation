package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	public WebDriver driver;

	public PageObjectManager(WebDriver driver) {
	
		this.driver=driver;
	}
  
	
	public LoginPage getLoginPageObject()
	{
		
		return new LoginPage(driver);
	}
	
	
	public AddEmployeePage getAddEmployeePage()
	{
		return new AddEmployeePage(driver);
	}
}
