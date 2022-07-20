package functionalTests.testsByBrian.tests.Cart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Full_cart {

	private String url = "";
	private WebDriver driver;
	private JavascriptExecutor js;
	private DriverClass driverClass;
	private List<WebElement> shoppingItemButtons;
	private List<List<WebElement>> shopContainers = Arrays.asList(Arrays.asList(new WebElement[30]));

	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");

		driver = driverClass.driver;
		js = driverClass.js;

		url = (url.isEmpty()) ? "http://opencart.qatestlab.net/index.php" : url;
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	@Ignore
	@Test(priority = 0)
	public void Add_1_product() throws Exception {

		// populate list with values
//		for(int i=0;i<shopContainers.size();i++) {
//			shopContainers.add(i, driver.findElements(By.xpath("//span[text()='Add to Cart']")));
//		}
		System.out.println();
		System.out.println("shop containers length: " + shopContainers.size());
		// get item containers and buttons within them
		shopContainers.forEach(container -> {

			container.addAll(driver.findElements(By.xpath("//span[text()='Add to Cart']")));
			System.out.println("<parent containers>");
			container.forEach(add2CartBtn -> {
				System.out.println("\t" + add2CartBtn);
			});
			System.out.println("</parent containers>");
		});

		// items up for sale on the site searched by buttons' xpath
		shoppingItemButtons = driver.findElements(By.xpath(
				"//*[@id=\"page\"]/section/div/div[number()]/div/div/div/div/div/div/div[number()]/div[number()]/div/div[number()]/div/button"));

		// //*[@id="page"]/section/div/div[4]

		// "//span[text()='Add to Cart']"));
		int randomIndex = (int) (Math.floor(Math.random() * (shoppingItemButtons.size())));

		// choose a random item and click 'Add to Cart' button with Javascript executor
		js.executeScript("arguments[0].click()", shoppingItemButtons.get(randomIndex));

		Thread.sleep(5000);
		System.out.println();
		shoppingItemButtons.forEach(item -> {
			System.out.println("Item: " + item + "\n");
		});
		System.out.println("No. of items found: " + shoppingItemButtons.size());
		System.out.println("Selected item: " + shoppingItemButtons.get(randomIndex));

//		// choose item preferences (colour and size)
//		new WebDriverWait(driver, Duration.ofSeconds(20))
//				.until(ExpectedConditions.visibilityOfElementLocated
//					(By.xpath("//*[@id=\"page\"]/div[4]/div/div/div")));

		// randomly select a cart item colour
		try {
			new Select(driver.findElement(By.id("sbSelector_18027191"))).selectByIndex((int) (Math.floor(
					Math.random() * ((List<WebElement>) driver.findElements(By.id("sbSelector_18027191"))).size())));
		} catch (NoSuchElementException e) {
			System.err.println("\nitem color not present");
		}

		// randomly select a cart item size
		try {
			new Select(driverClass.driver.findElement(By.id("sbSelector_16213551"))).selectByIndex((int) (Math.floor(
					Math.random() * ((List<WebElement>) driver.findElements(By.id("sbSelector_16213551"))).size())));
		} catch (Exception e) {
			System.err.println("\nitem size not present");
		}

		// proceed to the cart
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("#page > div.ajax-overlay.visible > div > div > div > button")));

		// click cart icon with Javascript executor and check the cart
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//*[@id=\"cart\"]/button")));

		WebElement cartProduct = null;
		cartProduct = driver.findElement(
				By.xpath("/html/body/div[1]/header/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/ul/li[1]"));
		assert cartProduct != null;
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
