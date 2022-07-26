package functionalTests.testsByBrian.tests.Cart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Full_cart {

	private String url = "";
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private DriverClass driverClass;
	private List<WebElement> itemBoxes, colors, sizes, options;

	public Full_cart() {
		driverClass = new DriverClass("chrome");

		driver = driverClass.driver;
		js = driverClass.js;

		PageFactory.initElements(driver, this);

		driver.manage().window().maximize();

		url = (url.isEmpty()) ? "http://opencart.qatestlab.net/index.php" : url;
	}

	@BeforeMethod
	public void beforeEach() {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(15000);
		driver.quit();
		driver = null;
	}

	@Test(priority = 0)
	public void Add_1_product() throws Exception {

		// get item containers and their relative buttons
		itemBoxes = driver.findElements(By.className("content"));

		// choose a random item and click 'Add to Cart' button with Javascript executor
		js.executeScript("arguments[0].click()", itemBoxes.get((int) (Math.floor(Math.random() * itemBoxes.size())))
				.findElement(By.className("btn-primary")));

		// randomly select a cart item colour and size
		driver.findElements(By.linkText("--- Please Select ---")).forEach(e -> {
			if(e!=null) {
				e.click();		
				e.findElement(By.className("sbOptions")).findElements(By.tagName("li")).get(2).click();
			}
		});

		// proceed to the cart

		// click cart icon with Javascript executor and check the cart
	}

	@Ignore
	@Test(priority = 1)
	public void Add_over_10_products() {

	}

	@Ignore
	@Test(priority = 2)
	public void Change_number_of_items_from_10_to_0() {

	}

	@Ignore
	@Test(priority = 3)
	public void Ability_to_return_to_the_basket_and_order_when_the_browser_is_unexpectedly_closed() {

	}

	@Ignore
	@Test(priority = 4)
	public void Remove_product_cross_checkbox_QTY_0() {

	}

	@Ignore
	@Test(priority = 5)
	public void Update_shopping_cart() {

	}

	@Ignore
	@Test(priority = 6)
	public void Continue_shopping() {

	}

	@Ignore
	@Test(priority = 7)
	public void Proceed_to_Checkout() {

	}
}
