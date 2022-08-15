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
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class Full_cart {

	private Boolean isSelected;
	private int index;
	private String url = "";
	private WebElement pNode, pNode2, pNode3;
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private DriverClass driverClass;
	private List<WebElement> itemBoxes, optChildren, options;

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
		Thread.sleep(8000);
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

		options = driver.findElements(By.linkText("--- Please Select ---"));

		if (options.size() > 0) {
			// randomly select a cart item colour and listSize
			options.forEach(select -> {
				if (select != null) {
					js.executeScript("arguments[0].click()", select);

					pNode = (WebElement) js.executeScript("return arguments[0].parentNode;", select);
					pNode2 = (WebElement) js.executeScript("return arguments[0].parentNode;", pNode);
					pNode3 = (WebElement) js.executeScript("return arguments[0].parentNode;", pNode2);

					isSelected = false;

					optChildren = pNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));

					// loop through each unordered list of product specifications
					optChildren.forEach(description -> {
						if (optChildren.size() > 1 && !isSelected) {
							index = (int) Math.floor(Math.random() * optChildren.size());
							optChildren.get((index <= 0) ? 1 : index).click();
							isSelected = true;
						} else if (optChildren.size() == 1 && !isSelected) {
							optChildren.get(0).click();
							isSelected = true;
						}
					});
				}
			});
			for (WebElement wb : pNode3.findElements(By.xpath("./child::*"))) {
				if (wb.getText().equalsIgnoreCase("Add to Cart"))
					js.executeScript("arguments[0].click()", wb);
			}
		}
	}
	
	@Ignore
	@Test(priority = 1)
	public void Add_over_10_products() throws InterruptedException {
		
		for (int i = 0; i < 10;Thread.sleep(1000), i++) {
			itemBoxes = driver.findElements(By.className("content"));

			// choose a random item and click 'Add to Cart' button with Javascript executor
			js.executeScript("arguments[0].click()", itemBoxes.get((int) (Math.floor(Math.random() * itemBoxes.size())))
					.findElement(By.className("btn-primary")));

			options = driver.findElements(By.linkText("--- Please Select ---"));

			if (options.size() > 0) {
				// randomly select a cart item colour and listSize
				options.forEach(select -> {
					if (select != null) {
						js.executeScript("arguments[0].click()", select);

						pNode = (WebElement) js.executeScript("return arguments[0].parentNode;", select);
						pNode2 = (WebElement) js.executeScript("return arguments[0].parentNode;", pNode);
						pNode3 = (WebElement) js.executeScript("return arguments[0].parentNode;", pNode2);

						isSelected = false;

						optChildren = pNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));

						// loop through each unordered list of product specifications
						optChildren.forEach(description -> {
							if (optChildren.size() > 1 && !isSelected) {
								index = (int) Math.floor(Math.random() * optChildren.size());
								optChildren.get((index <= 0) ? 1 : index).click();
								isSelected = true;
							} else if (optChildren.size() == 1 && !isSelected) {
								optChildren.get(0).click();
								isSelected = true;
							}
						});
					}
				});
				for (WebElement wb : pNode3.findElements(By.xpath("./child::*"))) {
					if (wb.getText().equalsIgnoreCase("Add to Cart"))
						js.executeScript("arguments[0].click()", wb);
				}
			}
		}
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
