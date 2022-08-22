package functionalTests.testsByBonface;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class SubscribeNewsLetter {
	String browser = "firefox";
	WebDriver driver;
	private DriverClass driverClass;
	private JavascriptExecutor js;
	String URL = "http://opencart.qatestlab.net/";

	@FindBy(xpath = "//*[@id=\"input-zemez-newsletter-email\"]")
	WebElement email;
	@FindBy(xpath = "//*[@id=\"zemez-newsletter-button\"]")
	WebElement clicksub;

	public SubscribeNewsLetter() {
		driverClass = new DriverClass(browser);
		driver = driverClass.driver;
		js = driverClass.js;
		PageFactory.initElements(driver, this);

	}

	@BeforeTest
	public void initURL() {
		driver.get(URL);
	}

	@Test(enabled=false)
	public void enterEmail() {
		email.sendKeys("John211@gmail.com");
	}

	@Test
	public void clicksubscribe() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clicksub);

		String expectedMessage = "The specified e-mail already subscribed";
		String text = clicksub.getText();
		Assert.assertTrue(text.contains(expectedMessage));

	}
}