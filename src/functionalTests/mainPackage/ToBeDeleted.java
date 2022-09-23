package functionalTests.mainPackage;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToBeDeleted {

	Thread thread1;

	WebDriver driver;
	JavascriptExecutor js;
	String url = "http://127.0.0.1:5500/";
	WebElement btn = null;

	public ToBeDeleted() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
	}

	@Test
	public void testButton() {
		driver.get(url);
//		driver.findElement(By.partialLinkText("click")).click();
		thread1 = new Thread() {
//			@Override
			public void run(){
				Boolean found = false;
				try {
					driver.findElement(By.partialLinkText("click"));
					found = true;
				}catch(NoSuchElementException ex){ ex.printStackTrace();};
				
				// Wait for 20 sec, if element is not available, quit
				if(found) {
					System.out.println("Button found!");
					btn = driver.findElement(By.partialLinkText("click"));
					btn.click();
					thread1.suspend();
				}
				System.out.println("Button Not found!");
			}
		};
		thread1.run();
//		btn.click();
//		thread1.suspend();
	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(20000);
		driver.quit();
		driver = null;
	}
}
