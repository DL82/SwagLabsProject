package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Cart;
import pageObjects.Checkout_Information;
import pageObjects.Checkout_Overview;
import pageObjects.Finish;
import pageObjects.General;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;

public class Checkout_Test extends Base {
	SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	Login l;
	Products p;
	Cart c;
	Item i;
	General g;
	
	
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

		l = new Login(driver);
		l.getUsername().sendKeys("standard_user");
		l.getPassword().sendKeys("secret_sauce");
		l.getSubmit().click();
	}
	
	@Test
	public void T02_selectSingleItem() throws InterruptedException {
		
		p = new Products(driver);
		i = new Item(driver);
		g = new General(driver);
		System.out.println("~~~ Scenario: complete flow of an item's purchase ~~~");
		softAssert.assertEquals("Products", p.getPageTitle().getText());
		System.out.println(driver.getCurrentUrl());	
		p.getItem1().click();
		i.getAdd().click();
		softAssert.assertEquals( "3",g.getBadge().getText());
		g.getCart().click();
		softAssert.assertAll();
	}
		
	@Test
	public void T03_continueToCheckout() throws InterruptedException {
		c = new Cart(driver);
		System.out.println(driver.getCurrentUrl());
		softAssert.assertEquals("Sauce Labs Bolt T-Shirt", c.getName().getText());
		softAssert.assertEquals( "15.99_123",c.getPrice().getText());
		//softAssert.assertAll();
		c.getCheckout().click();
		Thread.sleep(3000);
		System.out.println("~~~ End of Scenario: Item purchase flow completed successfully ~~~");
		softAssert.assertAll();
	}
	

	@Test
	public void T04_checkout_StepOne() throws InterruptedException {
		Checkout_Information ca1 = new Checkout_Information(driver);
		ca1.getSubmit().click();
		Assert.assertEquals("Error: First Name is required", ca1.getError().getText());
		Thread.sleep(2000);
		ca1.getFirstName().sendKeys("Dan");
		ca1.getSubmit().click();
		Assert.assertEquals("Error: Last Name is required", ca1.getError().getText());
		Thread.sleep(2000);
		ca1.getLastName().sendKeys("Lan");
		ca1.getSubmit().click();
		Assert.assertEquals("Error: Postal Code is required", ca1.getError().getText());
		Thread.sleep(2000);
		ca1.getPostalCode().sendKeys("123");
		ca1.getSubmit().click();
		
		Checkout_Overview ca2 = new Checkout_Overview(driver);
		Assert.assertEquals("Checkout: Overview", ca2.getPageTitle().getText());
		System.out.println(driver.getCurrentUrl());	
	}
	
	@Test
	public void T05_checkout_StepTwo() {
		Checkout_Overview ca2 = new Checkout_Overview(driver);
		
		
		Assert.assertEquals(ca2.getSubtotal().getText(), "Item total: $15.99");
		ca2.getFinish().click();
		
		Finish f = new Finish(driver);
		Assert.assertEquals("Finish", f.getPageTitle().getText());
		Assert.assertEquals("THANK YOU FOR YOUR ORDER", f.getThankYouHeader().getText());
	}
}
