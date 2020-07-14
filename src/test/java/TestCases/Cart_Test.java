package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Cart;
import pageObjects.General;
import pageObjects.Item;
import pageObjects.Login;
import pageObjects.Products;
import resources.Base;
import resources.CommonActions;


public class Cart_Test extends Base{

	WebDriver driver;
	CommonActions act = new CommonActions(driver);
	Login l = new Login(driver);
	Products p = new Products(driver);
	Cart c = new Cart(driver);
	Item i = new Item(driver);
	General g = new General(driver);
	
	
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
		
		act.addText(l.getUsername(), "standard_user");
		act.addText(l.getPassword(), "secret_sauce");
		act.click(l.getSubmit());
	}
	
	@Test
	public void T02_selectSingleItem() throws InterruptedException {
		
		p = new Products(driver);
		i = new Item(driver);
		g = new General(driver);
		
		Assert.assertEquals("Products", p.getPageTitle().getText());
			
		act.click(p.getItem4());
		act.click(i.getAdd());
		
		Assert.assertEquals(g.getBadge().getText(), "1");
		
		act.click(g.getCart());
		
		//p.getItem4().click();
		//i.getAddButton().click();
		//g.getCart().click();
	}
		
	@Test
	public void T03_continueToCheckout() throws InterruptedException {
		c = new Cart(driver);
		Assert.assertEquals("Sauce Labs Backpack",c.getName().getText());
		Assert.assertEquals("29.99",c.getPrice().getText());
		
		act.click(c.getCheckout());
		//c.getCheckout().click();
	}
}
