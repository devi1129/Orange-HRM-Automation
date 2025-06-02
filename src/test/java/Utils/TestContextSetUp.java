package Utils;

import pageObjects.PageObjectManager;

public class TestContextSetUp {

	public TestBase testBase;
	public PageObjectManager pgManager;

	public TestContextSetUp() {
		testBase = new TestBase();

		pgManager = new PageObjectManager(testBase.getdriver());
	}
      
	
	
}
