package stepDefinitions;

import Utils.TestContextSetUp;
import io.cucumber.java.After;


public class Hooks {
	
	
	TestContextSetUp testContextSetUp;

	public Hooks(TestContextSetUp tu) {
		this.testContextSetUp = tu;
	}

	@After
	public void afterscenario() {
		 testContextSetUp.testBase.getdriver().quit();
	}

	  
}
