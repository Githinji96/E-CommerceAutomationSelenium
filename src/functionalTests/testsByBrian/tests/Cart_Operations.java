package functionalTests.testsByBrian.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Cart_Operations {
	private DriverClass driverClass;
	private List<WebElement> shoppingItemButtons;
	
	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");	
		driverClass.driver.manage().window().maximize();
	}
	
	@BeforeMethod
	//@DataProvider(name="http://opencart.qatestlab.net/index.php")
	public void visit_url() {
		String url = "";
		url = (url.isEmpty())?"http://opencart.qatestlab.net/index.php":url;
		driverClass.driver.get(url);
	}
	
	@Test(priority=0)
	public void Add_1_product() {
		
		// items up for sale on the site searched by buttons' xpath
		shoppingItemButtons = driverClass.driver.findElements(By.xpath("//span[text()='Add to Cart']"));
		int randomIndex = (int) (Math.floor(Math.random()*(shoppingItemButtons.size())));

		// choose a random item and click 'Add to Cart' button with Javascript executor
		driverClass.js.executeScript("arguments[0].click()",shoppingItemButtons.get(randomIndex));
		
		// choose item preferences (colour and size)		
		try{
			new WebDriverWait(driverClass.driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"page\"]/div[4]/div/div/div")));
			
			// randomly select a cart item colour
			new Select(driverClass.driver.findElement
					(By.id("sbSelector_18027191"))).selectByIndex(
						(int) (Math.floor(Math.random()* 
							((List<WebElement>) driverClass.driver.findElements
									(By.id("sbSelector_18027191"))).size())));
			
			//randomly select a cart item size
			new Select(driverClass.driver.findElement
					(By.id("sbSelector_16213551"))).selectByIndex(
							(int) (Math.floor(Math.random()* 
									((List<WebElement>) driverClass.driver.findElements
											(By.id("sbSelector_16213551"))).size())));
	
		}catch(NoSuchElementException ex) {
			System.err.println(ex);
		}finally {
			try {
				// proceed to the cart
				driverClass.js.executeScript("arguments[0].click()", driverClass.driver
						.findElement(By.cssSelector("#page > div.ajax-overlay.visible > div > div > div > button")));
			}catch(NoSuchElementException ex) {
				System.err.println(ex);
			}
		}
		
		// click cart icon with Javascript executor and check the cart
		driverClass.js.executeScript("arguments[0].click()", driverClass.driver.findElement(By.xpath("//*[@id=\"cart\"]/button")));
		
		WebElement cartProduct=null;
		try{
			cartProduct = driverClass.driver.findElement
					(By.xpath("/html/body/div[1]/header/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/ul/li[1]"));
		}catch(NoSuchElementException ex) {
			System.err.println(ex);
		}finally {
			assert cartProduct!=null;
		}
	}
	@Ignore
	@Test(priority=1)
	public void Add_over_10_products() {
		
	}
	@Ignore
	@Test(priority=2)
	public void Change_number_of_items_from_10_to_0() {
		
	}
	@Ignore
	@Test(priority=3)
	public void Ability_to_return_to_the_basket_and_order_when_the_browser_is_unexpectedly_closed() {
		
	}
	@Ignore
	@Test(priority=4)
	public void Remove_product_cross_checkbox_QTY_0() {
		
	}
	@Ignore
	@Test(priority=5)
	public void Update_shopping_cart() {
		
	}
	@Ignore
	@Test(priority=6)
	public void Continue_shopping() {
		
	}
	@Ignore
	@Test(priority=7)
	public void Proceed_to_Checkout() {
		
	}

	@Test
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driverClass.driver.quit();
	}
}
