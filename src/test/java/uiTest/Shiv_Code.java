package uiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class Shiv_Code extends BaseTest {
	@Test
	public void Tooltip() {
		driver.get("http://testingpool.com/wp-content/uploads/2018/08/demo.html");
		WebElement dynamic_tooltip =driver.findElement(By.xpath("//a[@id='myTooltip']"));
		Actions action =new Actions(driver);
		action.moveToElement(dynamic_tooltip).build().perform();
		WebElement tootipdiv =driver.findElement(By.className("tooltip  ")); //unique
		System.out.println(tootipdiv.getText());
	}
	

}
