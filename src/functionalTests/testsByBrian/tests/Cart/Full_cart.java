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

	private Boolean isSelected;
	private int listSize = 0, index;
	private String url = "";
	private WebElement pNode, pNode2, pNode3;
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private DriverClass driverClass;
	private List<WebElement> itemBoxes, optChildren, sizes, options;

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
					
					System.out.println("== this is pNode3 \n"+pNode3.getText()+"\n ========================");

					isSelected = false;

					optChildren = pNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));

					// loop through each unordered list of product specifications
					optChildren.forEach(description -> {
						if (optChildren.size() > 1 && !isSelected) {

							index = (int) Math.floor(Math.random() * optChildren.size());

							js.executeScript("arguments[0].click()", optChildren.get((index <= 0) ? 1 : index));
							isSelected = true;
						} else if (optChildren.size() == 1 && !isSelected) {
							js.executeScript("arguments[0].click()", optChildren.get(0));
						}
					});
				}
				if (options.indexOf(select) == options.size() - 1) {
					
					WebElement btn = pNode3.findElement(By.xpath("//button/span[text()=\"Add to Cart\"]"));
					
					// Click 'Add to Cart' button to proceed "//button/span[text()=\"Add to Cart\"]"
					js.executeScript("arguments[0].click()", btn);

					// Then close the dialog if not null
					if (pNode2.findElement(By.xpath("//a[@class=\"ajax-overlay_close\"]")) != null) {
						try {
							Thread.sleep(1000);
							js.executeScript("arguments[0].click()",
									pNode2.findElement(By.xpath("//a[@class=\"ajax-overlay_close\"]")));
						} catch (InterruptedException e) {
							System.err.println(e);
						}
					}
				}

				System.out.println(pNode.findElement(By.tagName("ul")).getText() + " iteem");
				System.out.println();
			});

		}
	}

	@Ignore
	@Test(priority = 1)
	public void Add_over_10_products() throws InterruptedException {
		for(int i=0; i<10; Thread.sleep(100),i++) {
			
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
						
						System.out.println("== this is pNode3 \n"+pNode3.getText()+"\n ========================");

						isSelected = false;

						optChildren = pNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));

						// loop through each unordered list of product specifications
						optChildren.forEach(description -> {
							if (optChildren.size() > 1 && !isSelected) {

								index = (int) Math.floor(Math.random() * optChildren.size());

								js.executeScript("arguments[0].click()", optChildren.get((index <= 0) ? 1 : index));
								isSelected = true;
							} else if (optChildren.size() == 1 && !isSelected) {
								js.executeScript("arguments[0].click()", optChildren.get(0));
							}
						});
					}
					if (options.indexOf(select) == options.size() - 1) {
						
						WebElement btn = pNode3.findElement(By.xpath("//button/span[text()=\"Add to Cart\"]"));
						
						// Click 'Add to Cart' button to proceed "//button/span[text()=\"Add to Cart\"]"
						js.executeScript("arguments[0].click()", btn);

						// Then close the dialog if not null
						if (pNode2.findElement(By.xpath("//a[@class=\"ajax-overlay_close\"]")) != null) {
							try {
								Thread.sleep(1000);
								js.executeScript("arguments[0].click()",
										pNode2.findElement(By.xpath("//a[@class=\"ajax-overlay_close\"]")));
							} catch (InterruptedException e) {
								System.err.println(e);
							}
						}
					}

					System.out.println(pNode.findElement(By.tagName("ul")).getText() + " iteem");
					System.out.println();
				});

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
