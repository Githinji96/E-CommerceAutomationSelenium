package functionalTests.testsByBonface;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import functionalTests.mainPackage.DriverClass;

public class ExtentTestReports  {

	String browser = "firefox";
	WebDriver driver;
	WebElement element;
	JavascriptExecutor js;
	private DriverClass driverClass;
	String URL = "http://opencart.qatestlab.net/";
	
	ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("Extentreport.html");
    
    @FindBy(xpath = "//*[@id=\"stuck\"]/div/div/div/div/div/div/div/div/ul/li[3]/a/span")
	WebElement clickproductss;

	@FindBy(xpath = "//div[@id='content']//div[1]//div[1]//div[2]//div[1]//button[2]")
	WebElement pinkcolor;

	public ExtentTestReports() {
		driverClass = new DriverClass(browser);
		driver = driverClass.driver;
		js = driverClass.js;
		PageFactory.initElements(driver, this);
	}
		@BeforeMethod
		public void initURL() {
			driver.get(URL);
		}

	
	@BeforeSuite
	public void setups() {
       extent.attachReporter(spark);
	}

	@Test
	public void test1() {
//		List
		ExtentTest test1 = extent.createTest(Thread.class.getName()+"Add products");
		test1.log(Status.PASS, "Starting test case");
		test1.info("test executed");
		test1.pass("Test passed");
		test1.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test1.addScreenCaptureFromPath("screenshot.png");

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clickproductss);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	
	}

	@AfterSuite
	public void teardown() {
		extent.flush();
	}
}
