package TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageObjects.Login;
import pageObjects.Products;
import resources.Base;
import resources.ExtentReporter;

public class Login_Test extends Base {

	Logger log = LogManager.getLogger(Login_Test.class.getName());
	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	Login l;
	Products p;
	static ExtentReports extent;
	static ExtentTest test;

	@BeforeClass // getting the url from the properties file
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
	public void T01_invalidLoginAttempts(String username, String password, String description)
			throws IOException, InterruptedException {
		l = new Login(driver);
		extent = ExtentReporter.extentReportsGenerator();
		test = extent.createTest("Testcase 1 test 1 - Invalid credentials login attempts");
		addText(l.getUsername(), username);
		test.log(Status.INFO, "added username into text box");
		addText(l.getPassword(), password);
		test.log(Status.INFO, "added password into text box");
		click(l.getSubmit());
		test.log(Status.INFO, "clicked submit");
		log.debug("submitted invalid credentials. username: " + username + " , password: " + password);
		Thread.sleep(2000);
		Assert.assertTrue(getText(l.getError()).contains("Epic sadface"));
		log.info("Error message assertion passed. Error received: " + getText(l.getError()));
		test.log(Status.PASS, description + ", Login attempt failed");
		System.out.println("~~~ Case: " + description + ", Login attempt failed ~~~");

	}
	
	@Test(dataProvider = "validCredentials")
	public void T01_successfulLogin(String username, String password, String description) throws IOException {

		l = new Login(driver);
		addText(l.getUsername(), username);
		addText(l.getPassword(), password);
		click(l.getSubmit());
		log.debug("submitted valid credentials. username: " + username + " , password: " + password);
		p = new Products(driver);
		softAssert.assertEquals("Products", getText(p.getPageTitle()));
		log.info("Succesful login.Asserted page title: " + getText(p.getPageTitle()));
		System.out.println("~~~ Case: " + description + ", successful login completed ~~~");
		softAssert.assertAll();
	}

	/*** Initializing DataProvider annotation ***/
	@DataProvider
	public Object[][] invalidCredentials() {
		Object[][] data = { { "Daniel", "aaaa", "invalid credentials" },
				{ "bbbb", "secret_sauce", "only username is invalid" },
				{ "standard_user", "cccc", "only password is invalid" }, { "", "secret_sauce", "username is missing" },
				{ "standard_user", "", "password is missing" } };
		return data;
	}

	@DataProvider
	public Object[][] validCredentials() {

		Object[][] data = { { "standard_user", "secret_sauce", "valid credentials" } };
		return data;
	}
}
