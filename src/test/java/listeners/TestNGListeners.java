package listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import baseTest.BaseTest;

public class TestNGListeners extends BaseTest implements ITestListener{
	public void onFinish(ITestContext context) {
		extentTest.log(Status.INFO, "Test execution completed");
		extentReports.flush();
	}

	public void onStart(ITestContext context) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
		String locationOfScreenshot = System.getProperty("user.dir")+"/src/test/resources/screenshots/"+result.getName()+".png";
		try {
			FileUtils.copyFile(captureScreenshot(), new File(locationOfScreenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.log(Status.FAIL, result.getName()+" failed due to "+result.getThrowable());
		try {
			extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromPath(locationOfScreenshot).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReports.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName()+" is skipped");
		extentReports.flush();
	}

	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentReports.flush();
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" Passed");
		extentReports.flush();
	}
}
