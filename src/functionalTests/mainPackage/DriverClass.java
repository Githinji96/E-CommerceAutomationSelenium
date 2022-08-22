package functionalTests.mainPackage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverClass {

	public String browser;
	public WebDriver driver;
	public JavascriptExecutor js;
	public WebDriverWait wait;
	
	// browser variable holds the name of the browser
	public DriverClass(String browser) {
		this.browser = browser;
		setup();
	}
	
	// default browser-no-config
	public DriverClass() {
		this.browser = "firefox";
		setup();
	}
	@BeforeSuite
	@SuppressWarnings("deprecation")
	public void setup() {
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		js = (JavascriptExecutor) driver;
		wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
	}
}
