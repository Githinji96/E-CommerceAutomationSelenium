package functionalTests.testsByBonface;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddProductsIndex {
	String browser = "firefox";
	WebDriver driver;
	WebElement element;
	JavascriptExecutor js;
	private DriverClass driverClass;
	String URL = "http://opencart.qatestlab.net/";

	@FindBy(xpath = "//*[@id=\"stuck\"]/div/div/div/div/div/div/div/div/ul/li[3]/a/span")
	WebElement clickproductss;

	@FindBy(xpath = "//div[@id='content']//div[1]//div[1]//div[2]//div[1]//button[2]")
	WebElement pinkcolor;

	public AddProductsIndex() {
		driverClass = new DriverClass(browser);
		driver = driverClass.driver;
		js = driverClass.js;
		PageFactory.initElements(driver, this);

		js = (JavascriptExecutor) driver;

	}

	@BeforeMethod
	public void initURL() {
		driver.get(URL);
	}

	@Ignore
	@Test
	public void listItems() {
		List<WebElement> resultsList = driver.findElements(By.xpath("//*[@id=\"page\"]/section/div"));
		for (WebElement result : resultsList) {
			System.out.println(result.getText());

		}

	}
	// click small pets

	@Test
	public void clickproducts() {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clickproductss);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}
	// filter searches

	@Test
	public void clickPink() throws InterruptedException {
		 driver.get("http://opencart.qatestlab.net/index.php?route=product/category&path=32");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		  
		  js = (JavascriptExecutor) driver; js.executeScript("arguments[0].click();",
		  pinkcolor);
		 

	}

}
