package functionalTests.testsByBrian.tests.SignIn;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;

import functionalTests.mainPackage.*;

public class Validation_messages_for_required_fields {

	private String url = "";
	private WebDriver driver;
	private JavascriptExecutor js;
	private DriverClass driverClass;

	@Ignore
	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");

		js = driverClass.js;
		driver = driverClass.driver;

		url = (url.isEmpty()) ? "http://opencart.qatestlab.net/index.php" : url;
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

//	@Ignore
	@Test(priority = 0)
	public void Empty_fields() throws InterruptedException{
//		driver.navigate().to("http://opencart.qatestlab.net/index.php?route=account/login");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign In")).click();
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")));
	}

	@Ignore
	@Test(priority = 1)
	public void Incorrect_email() {

	}

	@Ignore
	@Test(priority = 2)
	public void Password_less_than_6_symbols() {

	}

	@Ignore
	@Test(priority = 3)
	public void Add_valid_data() {

	}
}
