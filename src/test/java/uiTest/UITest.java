package uiTest;

import org.testng.annotations.Test;

import baseTest.BaseTest;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import utility.TestDataUtility;

public class UITest extends BaseTest{
	@Test
	public void loginTestTwo() {
		if (!TestDataUtility.execution("loginTest")) {
			throw new SkipException("Skipping this test since it is not marked for execution");
		}
		System.out.println("Pass");
		Assert.assertEquals(true, true);
	}

	@Test
	public void simpleTest() {
		System.out.println("Hello");
		Assert.assertEquals(0, 0);

	}

}
