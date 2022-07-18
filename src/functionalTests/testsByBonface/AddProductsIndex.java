package functionalTests.testsByBonface;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddProductsIndex {
	String browser = "Edge";
	WebDriver driver;
	JavascriptExecutor js;
//   DriverClass driverClass = new DriverClass("firefox");
   
   @FindBy(xpath="body > div.rd-mobilemenu.active > ul > li:nth-child(2)")
   WebElement selectCats;
   
   @FindBy(xpath="//body/div[@id='page']/div[@id='product-category']/div[contains(@class,'row')]/div[@id='content']/div[contains(@class,'row')]/div[1]/div[1]/div[2]/div[1]/button[1]")
   WebElement clickProducts;
  
   @FindBy(xpath="//a[@class='sbFocus'][contains(text(),'Black')]")
   WebElement selectBlack;
   
   @FindBy(xpath="//div[contains(@class,'ajax-overlay visible')]//button[contains(@type,'button')]")
   WebElement clickButton;
   
   @BeforeClass
   public void beforeAll(){
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
	   js = (JavascriptExecutor) driver;
	   PageFactory.initElements(driver, this);
   }
   @AfterClass
   public void tearDown() {
	   driver.quit();
   }
   @Test(priority=0)
   public void selectcats() {
	   js.executeScript("arguments[0].click()", selectCats);
   }
   @Ignore
   @Test(priority=1)
   public void clickproduct() {
	   clickProducts.click();
   }
   @Ignore
   @Test(priority=2)
   public void selectblack() {
	   selectBlack.click();
   }
   @Ignore
   @Test(priority=3)
   public void clickButton() {
	   clickButton.click();
   }
}


