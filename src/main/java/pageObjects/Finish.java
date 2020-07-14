package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Finish {


WebDriver driver;
	
	private By pageTitle = By.cssSelector("div[class='subheader']");
	private By thankYouHeader = By.cssSelector("h2[class='complete-header']");
	private By finalLogo = By.cssSelector("div[class='pony_express']");
	
	public Finish(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	public WebElement getThankYouHeader() {
		return driver.findElement(thankYouHeader);
	}
	public WebElement getFinalLogo() {
		return driver.findElement(finalLogo);
	}
}
