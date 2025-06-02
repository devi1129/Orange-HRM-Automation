package stepDefinitions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

import Utils.PropertiesFile;
import Utils.TestContextSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

public class LoginPageStepDefinition {
	
	private static final Logger LOG=LogManager.getLogger(LoginPageStepDefinition.class);
	
	public TestContextSetUp context;
	
	public LoginPage loginPage;
	
	public LoginPageStepDefinition(TestContextSetUp context)
	{
		this.context=context;
	}
	
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
	  
		
	}
	
	
	@When("user clicks on login button by entering correct username and password")
	public void user_clicks_on_login_button_by_entering_correct_username_and_password() {
		loginPage=context.pgManager.getLoginPageObject();
		loginPage.EnterUsername(PropertiesFile.getProperty("username"));
		loginPage.EnterPassword(PropertiesFile.getProperty("password"));
		loginPage.ClickLogin();
		
	  
	}
	@Then("user should be directed to dashboard page")
	public void user_should_be_directed_to_dashboard_page() {
	   
		if(loginPage.IsDirected())
			LOG.info("Successful login and directed to Dashboard Page");
		
		else {
			LOG.error("Login was not successful");
			 Assert.fail("User was not directed to Dashboard Page after login");
		}
			
	}
	@When("user clicks on login button by entering incorrect username and correctpassword")
	public void user_clicks_on_login_button_by_entering_incorrect_username_and_correctpassword() {
		loginPage=context.pgManager.getLoginPageObject();
		loginPage.EnterUsername("test");
		loginPage.EnterPassword(PropertiesFile.getProperty("password"));
		loginPage.ClickLogin();
	  
	}
	@Then("user should get invalid credentials errors")
	public void user_should_get_invalid_credentials_errors() {
		
		if(loginPage.IsErrorShown())
			LOG.info("Invalid credentials message shown as incorrect username or password entered");	
		else {
			Assert.fail("Invalid credentials message not shown");
		}
	   
	}
	
	@When("user clicks on login button by entering correct username and incorrectpassword")
	public void user_clicks_on_login_button_by_entering_correct_username_and_incorrectpassword() {
		loginPage=context.pgManager.getLoginPageObject();
		loginPage.EnterUsername(PropertiesFile.getProperty("username"));
		loginPage.EnterPassword("test123");
		loginPage.ClickLogin();
	}
	
	
	@When("user clicks on login button by entering incorrect username and incorrectpassword")
	public void user_clicks_on_login_button_by_entering_incorrect_username_and_incorrectpassword() {
		loginPage=context.pgManager.getLoginPageObject();
		loginPage.EnterUsername("test123");
		loginPage.EnterPassword("test123");
		loginPage.ClickLogin();
	}

}
