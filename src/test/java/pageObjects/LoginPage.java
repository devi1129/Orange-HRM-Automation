package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@name='username']")
	WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//button[text()=' Login ']")
	WebElement loginbutton;
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement dashboardtext;
	
	@FindBy(xpath="//p[text()='Invalid credentials']")
	WebElement InvalidCredentialstext;

	public void EnterUsername(String name) {
		username.sendKeys(name);
	}

	public void EnterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void ClickLogin() {
		loginbutton.click();
	}
	
	public boolean IsDirected()
	{
		if(dashboardtext.isDisplayed())
			return true;
		
		return false;
	}
	
	public boolean IsErrorShown()
	{
		if(InvalidCredentialstext.isDisplayed())
			return true;
		
		return false;
	}
}
