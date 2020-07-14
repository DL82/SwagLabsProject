package TestCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.General;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class Products_Test extends Base {

	SoftAssert softAssert = new SoftAssert();
	Products prod;
	Item item;
	General g;
	Actions act;

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
		l.getUsername().sendKeys("standard_user");
		l.getPassword().sendKeys("secret_sauce");
		l.getSubmit().click();
	}

	@Test
	public void T02_getPageTitle() {
		prod = new Products(driver);
		Assert.assertEquals("Products", prod.getPageTitle().getText());
		System.out.println("Successful navigation to page: " + prod.getPageTitle().getText());
	}

	@Test
	public void T03_addItemsToCart_1() throws InterruptedException {
		System.out.println("~~~ Scenario: Add all items to cart ~~~");
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);

		prod.getItem0().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("1", g.getBadge().getText());
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "T03_addItemsToCart_1" })
	public void T03_addItemsToCart_2() throws InterruptedException {
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);
		
		prod.getItem1().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("2", g.getBadge().getText());
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "T03_addItemsToCart_2" })
	public void T03_addItemsToCart_3() throws InterruptedException {
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);
		
		prod.getItem2().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("3", g.getBadge().getText());
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "T03_addItemsToCart_3" })
	public void T03_addItemsToCart_4() throws InterruptedException {
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);

		prod.getItem3().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("4", g.getBadge().getText());
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "T03_addItemsToCart_4" })
	public void T03_addItemsToCart_5() throws InterruptedException {
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);
		
		prod.getItem4().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("5", g.getBadge().getText());
		Thread.sleep(2000);
	}

	@Test (dependsOnMethods={"T03_addItemsToCart_5"})
	public void T03_addItemsToCart_6() throws InterruptedException {
		prod = new Products(driver);
		item = new Item(driver);
		g = new General(driver);
		
		prod.getItem5().click();
		System.out.println(driver.getCurrentUrl());
		item.getAdd().click();
		System.out.println("Did we add item " + item.getName().getText() + " to the cart? "
				+ item.getRemove().isDisplayed());
		// System.out.println(item.getRemoveButton().isDisplayed());
		item.getBack().click();
		Assert.assertEquals("7", g.getBadge().getText());	
		Thread.sleep(2000);
		System.out.println("~~~ End of Scenario ~~~");
	}
}
