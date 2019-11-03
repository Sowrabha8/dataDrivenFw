package uiTest;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class TestExtentReport extends BaseTest {
	
	@Test
	public void loginTest() {
		System.out.println("Entered loginTest");
		driver.get("https://google.co.in");
		Assert.assertEquals(false, true);
	}

	@Test
	public void regUser() {
		System.out.println("Entered regUser Test");
		driver.get("https://google.co.in");
		Assert.assertEquals("selenium", "selenium");
	}

	@Test
	public void deleteUser() {
		System.out.println("Entered deleteUser test");
		driver.get("https://google.co.in");
		throw new SkipException("Skipping my test");
	}
}
