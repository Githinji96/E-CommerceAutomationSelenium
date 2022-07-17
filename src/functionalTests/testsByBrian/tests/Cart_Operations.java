package functionalTests.testsByBrian.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import functionalTests.mainPackage.DriverClass;

public class Cart_Operations {
	
	private DriverClass driverClass;
	
	@BeforeClass
	public void setup() {
		driverClass = new DriverClass("chrome");
	}
	
	@BeforeMethod
	public void visit_url(String url) {
		driverClass.driver.get(url);
	}
	
	@Test(priority=0)
	public void Add_1_product() {
		
	}
	
	@Test(priority=1)
	public void Add_over_10_products() {
		
	}
	
	@Test(priority=2)
	public void Change_number_of_items_from_10_to_0() {
		
	}
	
	@Test(priority=3)
	public void Ability_to_return_to_the_basket_and_order_when_the_browser_is_unexpectedly_closed() {
		
	}
	@Test(priority=4)
	public void Remove_product_cross_checkbox_QTY_0() {
		
	}
	@Test(priority=5)
	public void Update_shopping_cart() {
		
	}
	@Test(priority=6)
	public void Continue_shopping() {
		
	}
	@Test(priority=7)
	public void Proceed_to_Checkout() {
		
	}
}
