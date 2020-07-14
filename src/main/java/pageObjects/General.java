package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class General {
	
	WebDriver driver;
	//Shopping cart
	private  By cart = By.cssSelector("svg[data-icon='shopping-cart']");
	private  By badge = By.cssSelector("span[class='fa-layers-counter shopping_cart_badge']");
	// Burger menu
	private  By menu = By.cssSelector("div[class='bm-burger-button']");
	private  By items = By.cssSelector("a[id='inventory_sidebar_link']");
	private  By about = By.cssSelector("a[id='about_sidebar_link']");
	private  By reset = By.cssSelector("a[id='reset_sidebar_link']");
	private  By logout = By.cssSelector("a[id='logout_sidebar_link']");
	private  By close = By.linkText("Close Menu");
	// Footer icons
	private  By twitter = By.cssSelector("li[class='social_twitter']");
	private  By facebook = By.cssSelector("li[class='social_facebook']");
	private  By linkedin = By.cssSelector("li[class='social_linkedin']");
	

	public General(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getCart() {
		return driver.findElement(cart);
	}
	public WebElement getBadge() {
		return driver.findElement(badge);
	}	
	public WebElement getMenu() {
		return driver.findElement(menu);
	}
	public WebElement getItems() {
		return driver.findElement(items);
	}
	public WebElement getAbout() {
		return driver.findElement(about);
	}
	public WebElement getReset() {
		return driver.findElement(reset);
	}
	public WebElement getLogout() {
		return driver.findElement(logout);
	}
	public WebElement getClose() {
		return driver.findElement(close);
	}
	public WebElement getTwitter() {
		return driver.findElement(twitter);
	}
	public WebElement getFacebook() {
		return driver.findElement(facebook);
	}
	public WebElement getLinkedin() {
		return driver.findElement(linkedin);
	}
}
