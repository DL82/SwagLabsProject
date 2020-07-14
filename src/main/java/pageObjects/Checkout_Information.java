package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout_Information {

WebDriver driver;
	
	private By pageTitle = By.cssSelector("div[class='subheader']");
	private By firstName = By.cssSelector("input[data-test='firstName']");
	private By lastName = By.cssSelector("input[data-test='lastName']");
	private By postalCode = By.cssSelector("input[data-test='postalCode']");
	private By cancel = By.cssSelector("a[class='cart_cancel_link btn_secondary']");
	private By submit = By.cssSelector("input[type='submit']");
	private By error = By.cssSelector("h3[data-test='error']");
	
	
	

	public Checkout_Information(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	public WebElement getFirstName() {
		return driver.findElement(firstName);
	}
	public WebElement getLastName() {
		return driver.findElement(lastName);
	}
	public WebElement getPostalCode() {
		return driver.findElement(postalCode);
	}
	public WebElement getCancel() {
		return driver.findElement(cancel);
	}
	public WebElement getSubmit() {
		return driver.findElement(submit);
	}
	public WebElement getError() {
		return driver.findElement(error);
	}
	
}
