package functionalTests.testsByBrian.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

import functionalTests.mainPackage.*;

public class Sign_in {
	private DriverClass driverClass;
	
	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");
	}
	
	@Test(priority=0)
	public void Empty_fields() {
		
	}
	
	@Test(priority=1)
	public void Incorrect_email() {
		
	}
	
	@Test(priority=2)
	public void Password_less_than_6_symbols() {
		
	}
	
	@Test(priority=3)
	public void Add_valid_data() {
		
	}
}
