package uiTest;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class uiTesting123 {
	@Test
	public void uiTesting(){
		System.out.println("Successfully completeed");
	}
	@Test
	public void uiTest(){
		System.out.println("Successfully completeed");
	}
	

	@Test
	public void loginTest() {
		throw new SkipException("Skipped");
	}

	@Test
	public void logoutTest() {
		throw new SkipException("Skipped");
	}
}
