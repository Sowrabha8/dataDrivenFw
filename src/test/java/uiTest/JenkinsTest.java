package uiTest;

import java.io.FileNotFoundException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utility.PropertiesFileReader;

public class JenkinsTest extends BaseTest{
	@Test
	public void createJob() {
		String url = "";

		try {
			url = propReader.readPropertiesFile("url");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		driver.get(url);
		driver.findElement(By.name("j_username")).sendKeys("selenium");
		driver.findElement(By.name("j_password")).sendKeys("selenium123");
		driver.findElement(By.name("Submit")).click();
		driver.findElement(By.linkText("New Item")).click();
		driver.findElement(By.name("name")).sendKeys("firstJenkinsProj");
		driver.findElement(By.xpath("//li[contains(@class,'FreeStyleProject')]")).click();
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ok-button")));
		driver.findElement(By.id("ok-button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//button[text()='Add build step']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'menu-button-menu visible')]//ul[@class='first-of-type']"))));
		driver.findElement(By.linkText("Execute Windows batch command")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@name='builder']/table/tbody//div[@class='advancedLink']//button")));
		driver.findElement(By.name("command")).sendKeys("echo hello");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Disable Project']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("RSS for failures")));
		driver.findElement(By.linkText("Build Now")).click();
		//wait.until(arg0)
		
		
	}
}
