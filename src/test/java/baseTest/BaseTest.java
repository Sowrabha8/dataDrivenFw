package baseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import listeners.TestNGListeners;
import utility.ExcelReader;
import utility.PropertiesFileReader;

@Listeners(TestNGListeners.class)
public class BaseTest {
	public WebDriver driver;
	public WebDriverWait wait;
	public static ExcelReader excelReader;
	public static PropertiesFileReader propReader;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	@BeforeSuite
	public void initializeExtentReport() {
		System.out.println(System.getenv("PATH"));
		String pathToExtentReport = System.getProperty("user.dir")+"/src/test/resources/extentReports/extentReport.html";
		extentReports = new ExtentReports();
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(pathToExtentReport);
		extentHtmlReporter.config().setDocumentTitle("Smoke test");
		extentHtmlReporter.config().setReportName("Extent report");
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		extentReports.attachReporter(extentHtmlReporter);
	}

	@BeforeClass
	public void setUp() throws Exception {
		String execFolder = System.getProperty("user.dir")+"/src/test/resources/executables/";
		propReader = new PropertiesFileReader();
		String browser = System.getenv("BROWSER_NAME");
		if (browser.equalsIgnoreCase("Chrome")) {
			if (System.getProperty("os.name").contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", execFolder+"chromedriver.exe");
			}
			else if (System.getProperty("os.name").contains("Mac")) {
				System.setProperty("webdriver.chrome.driver", execFolder+"chromedriver_mac");
			}
			else {
				System.setProperty("webdriver.chrome.driver", execFolder+"chromedriver_linux");
			}

			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("Firefox")) {
			if (System.getProperty("os.name").contains("Windows")) {
				System.setProperty("webdriver.gecko.driver", execFolder+"geckodriver.exe");
			}
			else if (System.getProperty("os.name").contains("Mac")) {
				System.setProperty("webdriver.gecko.driver", execFolder+"geckodriver");
			}
			else {
				System.setProperty("webdriver.gecko.driver", execFolder+"geckodriver");
			}
			
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("internetexplorer")) {
			System.setProperty("webdriver.ie.driver", execFolder+"IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", execFolder+"msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(propReader.readPropertiesFile("implicit_wait")), TimeUnit.SECONDS);
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	public File captureScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return screenshot;
	}

	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		if (testResult.getStatus() == 3) {
			String locationOfScreenshot = System.getProperty("user.dir")+"/src/test/resources/screenshots/"+testResult.getName()+".png";
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(locationOfScreenshot));
			} catch (IOException e) {
				e.printStackTrace();
			}
			extentTest.log(Status.FAIL, testResult.getName()+" failed due to "+testResult.getThrowable());
			try {
				extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromPath(locationOfScreenshot).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			extentReports.flush();
		}
	}
}
