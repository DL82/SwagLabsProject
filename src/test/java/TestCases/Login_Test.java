package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Login;
import pageObjects.Products;
import resources.Base;
import resources.CommonActions;

public class Login_Test extends Base {

	WebDriver driver;
	CommonActions action;
	SoftAssert softAssert = new SoftAssert();
	Login l;
	Products p;

	@BeforeClass //getting the url from the properties file
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
	
	@AfterClass
	public void finalize() {
		driver.close();
	}
	
	@BeforeMethod
	public void openURL() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}
	
	@Test(dataProvider = "invalidCredentials")
	public void T01_invalidLoginAttempts(String username, String password, String description) throws IOException, InterruptedException {
		action = new CommonActions(driver);
		l = new Login(driver);
		action.addText(l.getUsername(), username);
		action.addText(l.getPassword(), password);
		action.click(l.getSubmit());
		Thread.sleep(2000);
		Assert.assertTrue(l.getError().contains("Epic sadface"));
		System.out.println("~~~ Case: " + description + ", Login attempt failed ~~~");
	}
	
	@Test(dataProvider = "validCredentials")
	public void T01_successfulLogin(String username, String password,String description) throws IOException {

		l = new Login(driver);
		action.addText(l.getUsername(), username);
		action.addText(l.getPassword(), password);
		action.click(l.getSubmit());
		
		p = new Products(driver);
		softAssert.assertEquals("Products_123123",p.getPageTitle().getText());
		System.out.println("~~~ Case: " + description + ", successful login completed ~~~");
		softAssert.assertAll();
	}
	
	// Initializing DataProvider annotation
	@DataProvider
	public Object[][] invalidCredentials() {
		Object[][] data = { 
				{ "Daniel", "aaaa", "invalid credentials"},
				{ "bbbb", "secret_sauce", "only username is invalid"},
				{ "standard_user", "cccc", "only password is invalid"},
				{ "", "secret_sauce", "username is missing"},
				{ "standard_user", "" ,"password is missing"}
		};
		return data;
	}

	@DataProvider
	public Object[][] validCredentials() {

		Object[][] data = {{ "standard_user", "secret_sauce", "valid credentials" }};
		return data;
	}
}
