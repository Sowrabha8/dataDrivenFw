package uiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.Retry;

public class RetryTest {
	@Test(retryAnalyzer=Retry.class)
	public void loginTest() {
		System.out.println("Executing loginTest");
		Assert.fail();
	}
}
