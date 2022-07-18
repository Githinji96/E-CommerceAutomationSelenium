package functionalTests.testsByBrian.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;

import functionalTests.mainPackage.*;

public class Sign_in {
	private DriverClass driverClass;
	
	@Ignore
	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");
		driverClass.driver.manage().window().maximize();
	}
	@Ignore
	@BeforeMethod
	@Parameters({"url","http://opencart.qatestlab.net/index.php"})
	public void visit_url(String url) {
		url = (url.isEmpty())?"http://opencart.qatestlab.net/index.php":url;
		driverClass.driver.get(url);
	}
	@Ignore
	@Test(priority=0)
	public void Empty_fields() {
		
	}
	@Ignore
	@Test(priority=1)
	public void Incorrect_email() {
		
	}
	@Ignore
	@Test(priority=2)
	public void Password_less_than_6_symbols() {
		
	}
	@Ignore
	@Test(priority=3)
	public void Add_valid_data() {
		
	}
}
