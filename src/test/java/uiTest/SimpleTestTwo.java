package uiTest;

import java.sql.Driver;

import org.testng.annotations.Test;

import baseTest.BaseTest;

public class SimpleTestTwo extends BaseTest{
	@Test
	public void simpleTestOne() {
		driver.get("https://google.co.in");
	}

	
	
}
