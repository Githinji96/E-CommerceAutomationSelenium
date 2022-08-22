package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class CaptureScreenshots extends DriverClass {

	@Test
	public void testScreenshot() throws Exception {
		Date currentDate = new Date();
 		String screenshots = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
		FileUtils.copyFile(screenshotFile,
				new File("D:\\gitRepoCart\\OpenCartAutomationTests\\screenshot\\screenshots.png"));

	}
}
