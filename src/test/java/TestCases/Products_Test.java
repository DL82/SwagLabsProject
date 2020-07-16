package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Cart;
import pageObjects.Global;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class Products_Test extends Base {

	SoftAssert softAssert = new SoftAssert();
	Products prod;
	Item item;
	Global glob;
	Cart cart;

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
		click(l.getSubmit());
		prod = new Products(driver);

		String expected = "Products";
		String actual = getText(prod.getPageTitle());
		Assert.assertEquals(actual, expected);

		System.out.println("Successful navigation to page: " + getText(prod.getPageTitle()));
	}

	@Test
	public void T02_addItemsToCart() throws InterruptedException {
		prod = new Products(driver);
		// item = new Item(driver);
		glob = new Global(driver);

		prod.clickAdd();

		String expected = "6";
		String actual = getText(glob.getBadge());
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void T03_orderByNameDescending() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.orderNameDesc();

		String expected = "Test.allTheThings() T-Shirt (Red)";
		String actual = prod.getFirstItem();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void T04_removeCurrentFirstIndex_1() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.removeFirstItem();

		String expected = "5";
		String actual = getText(glob.getBadge());
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void T05_orderByPriceAscending() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.orderPriceAsc();
		
		String expected = "Sauce Labs Onesie";
		String actual = prod.getFirstItem();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void T06_removeCurrentFirstIndex_2() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.removeFirstItem();

		String expected = "4";
		String actual = getText(glob.getBadge());
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void T07_orderByPriceDescending() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.orderPriceDesc();

		String expected = "Sauce Labs Fleece Jacket";
		String actual = prod.getFirstItem();
		Assert.assertEquals(actual, expected);
	}

	
	@Test
	public void T08_removeCurrentFirstIndex_3() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.removeFirstItem();

		String expected = "3";
		String actual = getText(glob.getBadge());
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void T09_orderByNameAscending() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.orderNameAsc();

		String expected = "Sauce Labs Backpack";
		String actual = prod.getFirstItem();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void T10_removeCurrentFirstIndex_4() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);

		prod.removeFirstItem();

		String expected = "2";
		String actual = getText(glob.getBadge());
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void T11_goToCart() throws InterruptedException {
		prod = new Products(driver);
		glob = new Global(driver);
		cart = new Cart(driver);
		
		click(glob.getCart());

		String expected = "Your Cart";
		String actual = getText(cart.getTitle());
		Assert.assertEquals(actual, expected);
	}
}
