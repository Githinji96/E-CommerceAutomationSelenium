package functionalTests.testsByBrian.tests.SignIn;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;

import functionalTests.mainPackage.*;

public class Validation_messages_for_required_fields {

	public int i = 0;

	private WebDriverWait wait;
	private String url = "";
	private String[] inputs, incorrectUserData, correctUserData, passTestData;
	
	private WebDriver driver;
	private JavascriptExecutor js;
	private DriverClass driverClass;
	private List<WebElement> requiredFields, regInputFields;

	@BeforeClass
	public void setup() {

		driverClass = new DriverClass("chrome");

		js = driverClass.js;
		driver = driverClass.driver;

		url = (url.isEmpty()) ? "http://opencart.qatestlab.net/index.php" : url;

		driver.manage().window().maximize();

		inputs = new String[] { "firstname", "lastname", "email", "telephone", "password", "confirm" };

		passTestData = new String[] { "Jane", "2", "jane54doe@email.com", "+258 745 214 458",
				"&itsApassword#$YesitIS2022", "&itsApassword#$YesitIS2022" };

		correctUserData = new String[] { "Jane", "Doe", "001janedoe@email.com", "+258 745 214 458",
				"&itsApassword#$YesitIS2022", "&itsApassword#$YesitIS2022" };

		incorrectUserData = new String[] { "Jane", "2", "invalid_email", "+258 745 214 458",
				"&itsApassword#$YesitIS2022", "&itsApassword#$YesitIS2022" };

		regInputFields = Arrays.asList(new WebElement[inputs.length]);
		requiredFields = Arrays.asList(new WebElement[inputs.length]);

		wait = driverClass.wait;
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@BeforeMethod
	public void launch_test() throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign In")).click();

		// Click Continue button
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")));
	}

	@AfterMethod
	public void delay() throws InterruptedException {
		// Reset iterator index
		i = 0;
		// 5 sec delay
		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void Empty_fields() throws InterruptedException {

		// Assert that we have the legit URl
		assert driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=account/register");

		// Click'Register' button
		Thread.sleep(100);
		js.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")));

		// Confirm successful registration
		requiredFields = driver.findElements(By.className("text-danger"));

		// Check for 'required warning' in all the fields
		requiredFields.forEach(field -> {
			if (field.getText().equals("This field is required.")) {
				assert true;
				return;
			}
			System.out.println(field.getText());
		});
	}

	@Test(priority = 1)
	public void Incorrect_email() throws InterruptedException {

		// Loop thro' the registration form and enter user credentials
		regInputFields.forEach(entry -> {
			entry = driver.findElement(By.name(inputs[i]));
			entry.sendKeys(incorrectUserData[i]);
			i++;
		});

		// Assert that an email format error is shown
		assert driver.findElement(By.id("input-email-error")).getText().equals("Please enter a valid email address.");
	}


	@Test(priority = 2)
	public void Password_less_than_6_symbols() {

		// Loop thro' the registration form and enter user credentials
		regInputFields.forEach(entry -> {
			entry = driver.findElement(By.name(inputs[i]));
			entry.sendKeys(correctUserData[i]);
			i++;
		});

		// Type a weak password
		WebElement passwd = driver.findElement(By.id("input-password"));
		driver.findElement(By.id("input-confirm")).clear();
		passwd.clear();
		passwd.sendKeys("pas");

		// Assert that an password too weak error is shown
		assert driver.findElement(By.id("input-password-error")).getText().equals("Please enter at least 4 characters.");
	}

	@Test(priority = 3)
	public void Add_valid_data() throws InterruptedException {

		// Assert that we have the legit URl
		Thread.sleep(2000);
		assert driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=account/register");

		// Loop thro' the registration form and enter user credentials
		regInputFields.forEach(entry -> {
			entry = driver.findElement(By.name(inputs[i]));
			entry.sendKeys(passTestData[i]);
			i++;
		});

		// Agree to site terms and conditions
		driver.findElement(By.name("agree")).click();

		// Click'Register' button
		Thread.sleep(100);
		js.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")));
		
		// Confirm successful registration
		assert driver.getCurrentUrl().equals("http://opencart.qatestlab.net/index.php?route=account/success");
	}
}
