package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.TestBase;
import Utils.TestContextSetUp;

public class AddEmployeePage extends BasePage {

	public AddEmployeePage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement PIMModule;

	@FindBy(xpath = "//li/a[text()='Add Employee']")
	WebElement AddEmployee;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement FirstName;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement MiddleName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement LastName;

	@FindBy(xpath = "(//div/label[text()='Employee Id']//following::input)[1]")
	WebElement EmployeeID;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement LoginDetails;

	@FindBy(xpath = "(//div/label[text()='Username']//following::input)[1]")
	WebElement Username;

	String statusxpath = "//input[@type='radio' and @value='%s']";

	@FindBy(xpath = "(//div/label[text()='Password']//following::input)[1]")
	WebElement Password;

	@FindBy(xpath = "(//div/label[text()='Confirm Password']//following::input)[1]")
	WebElement confirmPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement Save;

	@FindBy(xpath = "//h6[text()='Personal Details']")
	WebElement personaldetailsdisplayed;

	public void ClickPimModule() {

		clickelement(PIMModule);
	}

	public void ClickAddEmployee() {
		clickelement(AddEmployee);
	}

	public void EnterFirstName(String Firstname) {
		FirstName.sendKeys(Firstname);
	}

	public void EnterMiddleName(String Middlename) {
		MiddleName.sendKeys(Middlename);
	}

	public void EnterLastName(String Lastname) {
		LastName.sendKeys(Lastname);
	}

	public void EnterEmployeeID(String Employeeid) {
		EmployeeID.clear();
		EmployeeID.sendKeys(Employeeid);
	}

	public void CreateLogin() {
		clickelement(LoginDetails);
	}

	public void EnterUsername(String userName) {
		Username.sendKeys(userName);
	}

	public void statusclick(String valuenum) {

		String xpath = statusxpath.replace("%s", valuenum);

		clickelement(driver.findElement(By.xpath(xpath)));
	}

	public void EnterPassword(String pwd) {
		Password.sendKeys(pwd);
	}

	public void EnterConfirmPassword(String pwd) {
		confirmPassword.sendKeys(pwd);
	}

	public void ClickSave() {
		clickelement(Save);
	}

	public boolean savedsucess() {
		if (personaldetailsdisplayed.isDisplayed())

			return true;

		return false;
	}
}
