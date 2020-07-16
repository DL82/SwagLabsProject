package TestCases;

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
import pageObjects.Finish;
import pageObjects.Global;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class CheckoutOverview_Test extends Base {

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
	Finish f;
	
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
	public void T02_selectItem() throws InterruptedException {
		i = new Item(driver);
		g = new Global(driver);
		
		int listSize = p.getAdd().size();
		int randomListIndex = (int)Math.floor(Math.random() * listSize);
		/** expected listSize is 6 , expected random index is between 0 to 5 to be used as get(index) values below  **/
		
		WebElement randomAddButton = p.getAdd().get(randomListIndex);
		
		String randomName = p.getName().get(randomListIndex).getText();
		itemName = randomName; 
		
		String randomPrice = p.getPrice().get(randomListIndex).getText();
		itemPrice = randomPrice;
		
		randomAddButton.click();
		System.out.println("Item added to cart: " + itemName + " - " + itemPrice);
		g.getCart().click();

		int expected = 9999; //valid expected: 1
		int actual = Integer.parseInt(g.getBadge().getText());
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
		System.out.println("Item name and price from Products page asserted successfully in Cart page");
		log.info("Item name and price from Products page asserted successfully in the Cart page");
		Thread.sleep(2000);
		}
	
	@Test
	public void T04_checkoutInfo() throws InterruptedException {
		c = new Cart(driver);
		ci = new Checkout_Information(driver);
		co = new Checkout_Overview(driver);
		
		c.getCheckout().click();
		addText(ci.getFirstName(),"My");
		addText(ci.getLastName(),"Test");
		addText(ci.getPostalCode(),"Details");
		ci.getSubmit().click();
		
		String expected = "Checkout: Overview";
		String actual = co.getPageTitle().getText();
		Assert.assertEquals(actual,expected);
		System.out.println(driver.getCurrentUrl());	
	}

	@Test
	public void T05_checkoutOverview() throws InterruptedException {
		co = new Checkout_Overview(driver);
		
		String nameInOverviewPage = co.getName().get(0).getText();
		String priceInOverviewPage = co.getPrice().get(0).getText();
		softAssert.assertEquals(nameInOverviewPage, itemName);
		softAssert.assertEquals(priceInOverviewPage, itemPrice);
		System.out.println("Item name and price from the Products page asserted successfully in Overview page");
		log.info("Item name and price from the Products page asserted successfully in Overview page");
		
		String expected = "SauceCard #31337";
		String actual = co.getPaymentInfo().getText();
		softAssert.assertEquals(actual, expected);
		softAssert.assertAll();
		Thread.sleep(1000);
		}

	@Test
	public void T06_goToFinish() {
	
		co = new Checkout_Overview(driver);
		f = new Finish(driver);
				
		co.getFinish().click();
		
		Assert.assertEquals(f.getPageTitle().getText(),"Wrong title!!!");	//valid String: "Finish"
	}
}