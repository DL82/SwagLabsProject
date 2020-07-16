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

import pageObjects.Cart;
import pageObjects.Checkout_Information;
import pageObjects.Global;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class Cart_Test extends Base {

	Logger log = LogManager.getLogger(Cart_Test.class.getName());
	WebDriver driver;
	Login l = new Login(driver);
	Products p = new Products(driver);
	Cart c;
	Item i = new Item(driver);
	Global g = new Global(driver);
	Checkout_Information ci;
	
	String randomName = null;

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
	public void T02_selectRandomItem() throws InterruptedException {
		i = new Item(driver);
		g = new Global(driver);
		c = new Cart(driver);

		// click a random item name - redirect to item's page
		WebElement random = p.getRandom();
		String nameProductPage = random.getText();
		
		//assigning the random product name selected from the products page into a global variable
		randomName = nameProductPage; 
		
		System.out.println("Selecting random item: " + random.getText());
		random.click();
		i.getAdd().click();
		String nameItemPage = i.getName().getText();
		
		Assert.assertEquals(nameProductPage, nameItemPage);
		System.out.println("Current Item's page: " + nameItemPage);
	}

	@Test
	public void T03_checkCart() throws InterruptedException {
		p = new Products(driver);
		g = new Global(driver);
		c = new Cart(driver);

		g.getCart().click();
		System.out.println("Current page: " + getText(c.getTitle()));
		int expected = 1;
		int actual = Integer.parseInt(g.getBadge().getText());
		Assert.assertEquals(actual, expected);
		
		String nameInCartPage = c.getName().get(0).getText();
		Assert.assertEquals(nameInCartPage, randomName);
		log.info("Name passed from item's page asserted successfully with name in cart");
		Thread.sleep(2000);
		}


	@Test
	public void T04_continueShopping() throws InterruptedException {
		p = new Products(driver);
		g = new Global(driver);
		c = new Cart(driver);

		c.getContinueShopping().click();
		p.addAllItems();
		System.out.println("Current page: " + getText(p.getPageTitle()));
		g.getCart().click();
		Thread.sleep(2000);
		int expected = 6;	
		int cartSize = c.getItemBlock().size();
		int cartBadge = Integer.parseInt(g.getBadge().getText());
		Assert.assertEquals(cartSize, expected);
		Assert.assertEquals(cartBadge, expected);
		System.out.println("Current page: " + getText(c.getTitle()));
	}

	@Test
	public void T05_goToCheckOut() throws InterruptedException {
		p = new Products(driver);
		g = new Global(driver);
		c = new Cart(driver);
		ci = new Checkout_Information(driver);
		
		click(c.getCheckout());
		System.out.println("Current page: " + getText(ci.getPageTitle()));
		String expected = "Invalid title!!! "; //valid title - "Checkout: Your Information" 
		String actual = getText(ci.getPageTitle());
		Assert.assertEquals(actual, expected);
		Thread.sleep(2000);
		

	}
}
