package functionalTests.testsByBrian.tests.SignIn;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Returning_customer {
	
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
	
	@Ignore
	@Test(priority=0)
	public void first_action() {
		System.out.println("first action of returnong customer class");
	}
}
