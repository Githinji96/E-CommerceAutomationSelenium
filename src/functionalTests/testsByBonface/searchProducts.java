package functionalTests.testsByBonface;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class searchProducts {
	String browser = "firefox";
	WebDriver driver;
	private DriverClass driverClass;
	private JavascriptExecutor js;
	String URL = "http://opencart.qatestlab.net/";

	@FindBy(xpath = "//*[@id=\"search\"]/input")
	WebElement searchFish;

	@FindBy(xpath = "//body/div[@id='page']/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@class='row']/div[1]/div[1]/div[2]/div[1]/button[1]")
	WebElement addproduct;

	@FindBy(xpath = "//*[@id=\"cart-total2\"]")
	WebElement cartclick;

	@FindBy(xpath = "//*[@id=\"cart\"]/ul/li[3]/div/a[1]")
	WebElement cartview;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/div/form/div/table/tbody/tr/td[4]/div/input")
	WebElement editQuantity;

	@FindBy(xpath = "//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/span/button[1]")
	WebElement updateQuantity;

	@FindBy(xpath="/html/body/div[1]/div[3]/div[3]/div/form/div/table/tbody/tr/td[6]")
    WebElement assertPrice;

	public searchProducts() {
		driverClass = new DriverClass(browser);
		driver = driverClass.driver;
		js = driverClass.js;
		PageFactory.initElements(driver, this);
	}

	@BeforeTest
	public void initURL() {
		driver.get(URL);
	}

	@Test(priority = 0)
	public void searchField() {
		searchFish.sendKeys("Fish" + Keys.ENTER);

	}

	@Test(priority = 1)
	public void addProducts() {
		// addProducts.click();
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", addproduct);
	}

	@Test(priority = 2)
	public void clickCart() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cartclick);
	}

	@Test(priority = 3)
	public void clickview() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cartview);
	}

	@Test(priority = 4)
	public void editCartQuantity() {
		editQuantity.clear();
		editQuantity.sendKeys("5");

	}

	@Test(priority = 5)
	public void updateQuantity() {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", updateQuantity);
	}

	@Test(priority = 6)
	public void assertTotalPrice() {
		String price = assertPrice.getText();
		Assert.assertEquals(price, "$160.00");
	}
}
