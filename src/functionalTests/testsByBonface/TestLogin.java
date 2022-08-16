package functionalTests.testsByBonface;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class TestLogin {
	String browser = "firefox";
	WebDriver driver;
	WebElement element;
	JavascriptExecutor js;
	private DriverClass driverClass;
	String URL = "http://opencart.qatestlab.net/index.php?route=account/login";

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement enterEmail;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement enterPassword;
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
	WebElement clickButton;

	public TestLogin() {
		driverClass = new DriverClass(browser);
		driver = driverClass.driver;
		js = driverClass.js;
		PageFactory.initElements(driver, this);

		js = (JavascriptExecutor) driver;

	}

	@BeforeMethod
	public void enterURL() {
		driver.get(URL);
	}

	@Test(dataProvider = "credentials")
	public void verifyLogin(String scenario, String email, String password) throws InterruptedException {
		enterEmail.sendKeys(email);
		enterPassword.sendKeys(password);

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clickButton);
        
		if(scenario.equals("bothCorrect")) {
			String title=driver.getTitle();
			String expectedtitle="My Account";
			Assert.assertEquals(title,expectedtitle);
		}
		else if(scenario.equals("bothWrong")) {
			String errorMessage=driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
			String expectedText="Warning: No match for E-Mail Address and/or Password";
			Assert.assertEquals(errorMessage,expectedText);
		}
	}

	@DataProvider(name = "credentials")
	public Object[][] getData() {
		return new Object[][] { { "bothCorrect", "mansurgithinji73@gmail.com", "DF12345#" },
				{ "bothWrong", "swahib23@gmail.com", "WR213" },
				{ "correctpassword", "swahili45@yahoo.com", "DF12345#" },
				{ "correctEmail", "mansurgithinji73@gmail.com", "DE12345" } };

	  
	}
}
