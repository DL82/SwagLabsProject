package TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Cart;
import pageObjects.Checkout_Information;
import pageObjects.Checkout_Overview;
import pageObjects.Global;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class CheckoutInformation_Test extends Base {
	
	Logger log = LogManager.getLogger(CheckoutInformation_Test.class.getName());
	SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	Login l;
	Products p;
	Cart c;
	Item i;
	Global g;
	Checkout_Information ci;
	Checkout_Overview co;
	
	String itemName = null;
	String itemPrice = null; 
	
	@BeforeClass
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	

	@AfterClass
	public void finalize() {
		driver.close();
	}
	
	
	@Test
	public void T01_validLogin() {
		Login l = new Login(driver);
		addText(l.getUsername(), "standard_user");
		addText(l.getPassword(), "secret_sauce");
		l.getSubmit().click();
		p = new Products(driver);

		String expected = "Products";
		String actual = getText(p.getPageTitle());
		Assert.assertEquals(actual, expected);

		System.out.println("Current page: " + getText(p.getPageTitle()));
	}

	@Test
	public void T02_selectFirstItem() throws InterruptedException {
		i = new Item(driver);
		g = new Global(driver);
		

		WebElement firstAddButton = p.getAdd().get(0);
		
		String firstName = p.getFirstItem();
		itemName = firstName; 
		
		String firstPrice = p.getFirstPrice();
		itemPrice = firstPrice;
		
		firstAddButton.click();
		System.out.println("Item added to cart: " + itemName + " - " + itemPrice);
		g.getCart().click();
		Thread.sleep(1000);
	//	System.out.println("Current page: " + c.getTitle().getText());
		String expected = "1";
		String actual = g.getBadge().getText();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void T03_checkItemInCart() throws InterruptedException {
		p = new Products(driver);
		g = new Global(driver);
		c = new Cart(driver);

		String nameInCartPage = c.getName().get(0).getText();
		String priceInCartPage = c.getPrice().get(0).getText();
		Assert.assertEquals(nameInCartPage, itemName);
		Assert.assertEquals("$"+priceInCartPage, itemPrice);
		System.out.println("Item name and price from the products page asserted successfully in the cart page");
		log.info("Item name and price from the products page asserted successfully in the cart page");
		Thread.sleep(2000);
		}
	
	@Test
	public void T04_checkoutInfo() throws InterruptedException {
		
		c = new Cart(driver);
		ci = new Checkout_Information(driver);
		co = new Checkout_Overview(driver);
		
		c.getCheckout().click();
		ci.getSubmit().click();
		String expected = "Error: First Name is required";
		String actual = ci.getError().getText();
		Assert.assertEquals(actual,expected);
		Thread.sleep(1000);
		
		ci.getCancel().click();
		assertTrue(c.getTitle().isDisplayed(), "Cart page title is displayed");
		
		c.getCheckout().click();
		assertTrue(c.getTitle().isDisplayed(), "Checkout page title is displayed");
		
		addText(ci.getFirstName(),"My");
		ci.getSubmit().click();
		expected = "Error: Last Name is required";
		actual = ci.getError().getText();
		Assert.assertEquals(actual,expected);
		Thread.sleep(1000);
		
		addText(ci.getLastName(),"Test");
		ci.getSubmit().click();
		expected = "Error: Postal Code is required";
		actual = ci.getError().getText();
		Assert.assertEquals(actual,expected);
		Thread.sleep(1000);
		
		addText(ci.getPostalCode(),"Details");
		ci.getSubmit().click();
		
		expected = "Checkout: Overview";
		actual = co.getPageTitle().getText();
		Assert.assertEquals(actual,expected);
		System.out.println(driver.getCurrentUrl());	
	}
}
