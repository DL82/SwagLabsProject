package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	WebDriver driver;
	
	private By username = By.cssSelector("input[data-test='username']");
	private By password = By.cssSelector("input[data-test='password']");
	private By submit = By.cssSelector("input[value='LOGIN']");
	private By error = By.cssSelector("h3[data-test='error']");
	
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public WebElement getUsername() {
		return driver.findElement(username);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getSubmit() {
		return driver.findElement(submit);
	}
	
	public WebElement getError() {
		return driver.findElement(error);
	}
	
}
