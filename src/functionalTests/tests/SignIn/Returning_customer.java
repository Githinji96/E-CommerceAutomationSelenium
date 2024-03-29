package functionalTests.tests.SignIn;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Returning_customer {

	private String url = "http://opencart.qatestlab.net/index.php";
	private String browser = "chrome";
	private String email = "jane54doe@email.com";
	private String passWd = "&itsApassword#$YesitIS2022";
	private boolean skipAfterTest = false;
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private DriverClass driverClass;

	@FindBy(xpath = "//*[@id=\"input-email\"]")
	private WebElement emailField;

	@FindBy(xpath = "//*[@id=\"input-password\"]")
	private WebElement passwdField;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
	private WebElement submitBtn;

	public Returning_customer() {
		driverClass = new DriverClass(browser);
		js = driverClass.js;
		driver = driverClass.driver;

		PageFactory.initElements(driver, this);
	}

	@BeforeClass
	public void beforeClass() {

		js = driverClass.js;
		driver = driverClass.driver;
		wait = driverClass.wait;

		url = (url.isEmpty()) ? "http://opencart.qatestlab.net/index.php" : url;

		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() throws InterruptedException, IOException {
		Thread.sleep(3000);
		//driver.quit();
		//driver = null;
	}

	@BeforeMethod
	public void beforeEach() throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);
		// Click sign in
		driver.findElement(By.partialLinkText("Sign ")).click();
	}

	@AfterMethod
	public void afterEach() throws InterruptedException {

		if (!skipAfterTest) {
			// Click sign out
			js.executeScript("arguments[0].click()",
					wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Sign "))));
			// Delay
			Thread.sleep(2000);
			skipAfterTest = true;
		}
	}

	@Test(priority = 0)
	public void Empty_returning_customer_form_fields() {
		// Click Continue button
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")));

		// Check for errors
		assert driver.findElement(By.className("fa-exclamation-circle")) != null
				|| driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]/text()")).getText()
						.equals("Warning: No match for E-Mail Address and/or Password.");

		// Skip afterTest annotation
		skipAfterTest = true;
	}

	@Test(priority = 1)
	public void Valid_returning_customer_form_fields() throws InterruptedException {

		// Type email for a returning customer
		emailField.sendKeys(email);

		// Type email for a returning customer
		passwdField.sendKeys(passWd);

		// Submit the form
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();

		// Confirm successful login
		assert driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=account/account");
	}

	@Test(priority = 2)
	public void Forgotten_password_recovery() throws InterruptedException {

		// Head straight to login URL
		driver.navigate().to("http://opencart.qatestlab.net/index.php?route=account/login");
		
		// Click 'forgot my password'
		js.executeScript("arguments[0].click()", driver.findElement(By.partialLinkText("Forgotten ")));
		Thread.sleep(2000);

		// Type the user email
		driver.findElement(By.id("input-email")).sendKeys(email);

		// Confirm email reset link has been sent
		assert driver.findElement(By.className("fa-check-circle")).getText()
				.equals("An email with a confirmation link has been sent your email address.");

		// Click 'CONTINUE'
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
	}

	@Test(priority = 3)
	public void Sign_out() {

		// Log in
		emailField.sendKeys(email);
		passwdField.sendKeys(passWd);

		// Submit the form
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();

		System.out.println("\n\n\n" + driver.getCurrentUrl() + "\n\n\n\n");

		// Click 'Sign Out'
		js.executeScript("arguments[0].click()", driver.findElement(By.partialLinkText("Sign ")));

		// Click 'CONTINUE' with JavaScriptExecutor
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")));

		// Confirm user sign out
		assert driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=common/home")
				|| driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=common/login");

		// Skip afterTest annotation
		skipAfterTest = false;
	}

	@Ignore
	@Test(priority = 4)
	public void newTestCase() {

	}
}
