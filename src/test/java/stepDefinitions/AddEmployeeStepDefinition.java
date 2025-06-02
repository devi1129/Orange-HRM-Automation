package stepDefinitions;

import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import Utils.ExcelUtils;
import Utils.TestContextSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddEmployeePage;

public class AddEmployeeStepDefinition {

	private static final Logger LOG = LogManager.getLogger(AddEmployeeStepDefinition.class);

	public TestContextSetUp context;

	public AddEmployeePage Employeepage;

	public AddEmployeeStepDefinition(TestContextSetUp context) {
		this.context = context;
	}

	@Given("user is on  add employee page in PIM module")
	public void user_is_on_add_employee_page_in_pim_module() {

		Employeepage = context.pgManager.getAddEmployeePage();
		Employeepage.ClickPimModule();
		Employeepage.ClickAddEmployee();

	}

	@When("user enter employee data from {string}")
	public void user_enter_employee_data_from(String sheet) {

		List<Map<String, String>> sheetData = ExcelUtils.getSheetDataAsListOfMaps(sheet);

		int rowcount = sheetData.size()+1;
		

		for (int i = 1; i < rowcount; i++) {
			
			
			Map<String, String> rowData = sheetData.get(i-1);

			String firstname = context.testBase.CreatefirstName();

			ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "First Name"), firstname);
			Employeepage.EnterFirstName(firstname);

			String MiddleName = context.testBase.CreateLastName();

			ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "Middle Name"), MiddleName);
			Employeepage.EnterMiddleName(MiddleName);

			String LastName = context.testBase.CreateLastName();

			ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "Last Name"), LastName);
			Employeepage.EnterLastName(LastName);

			String EmployeeID = context.testBase.createid();

			ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "Employee ID"), EmployeeID);
			Employeepage.EnterEmployeeID(EmployeeID);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(rowData.get("Create Login Details"));
			if (rowData.get("Create Login Details").equalsIgnoreCase("Yes")) {
				Employeepage.CreateLogin();
				String username = context.testBase.Createusername();

				ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "User Name"), username);
				Employeepage.EnterUsername(username);

				if (rowData.get("Status").equalsIgnoreCase("Disabled"))
					Employeepage.statusclick("2");

				String password = context.testBase.Createpassword();

				ExcelUtils.setCellData(sheet, i, ExcelUtils.getCellIndexByHeader(sheet, "Password"), password);
				Employeepage.EnterPassword(password);

				Employeepage.EnterConfirmPassword(password);
				Employeepage.ClickSave();

			}
			else {
				Employeepage.ClickSave();
			}
			if(Employeepage.savedsucess()) {
				LOG.info("Employee Details saved successfully");
			Employeepage.ClickAddEmployee();
			
			}
			else {
				LOG.error("Employee Details not saved");
				Assert.fail("Employee Details not saved");
		
			}
		

	}
		}
}
