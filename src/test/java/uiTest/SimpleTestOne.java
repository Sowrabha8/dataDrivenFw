package uiTest;

import java.sql.Driver;

import org.testng.annotations.Test;

import baseTest.BaseTest;

public class SimpleTestOne extends BaseTest{
	@Test
	public void simpleTestOne() {
		driver.get("https://facebook.com");
	}
}
