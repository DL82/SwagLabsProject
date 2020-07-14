package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {

	WebDriver driver;
	
	private By checkout = By.cssSelector("a[class='btn_action checkout_button']");
	private By remove = By.cssSelector("a[class='btn_secondary cart_button']");
	private By name = By.cssSelector("div[class='inventory_item_name']");
	private By price = By.cssSelector("div[class='inventory_item_price']");
	private By continueShopping = By.cssSelector("a[class='btn_secondary']");
	
	
	public Cart(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getCheckout() {
		return driver.findElement(checkout);
	}
	public WebElement getRemove() {
		return driver.findElement(remove);
	}
	public WebElement getName() {
		return driver.findElement(name);
	}
	public WebElement getPrice() {
		return driver.findElement(price);
	}
	public WebElement getContinueShopping() {
		return driver.findElement(continueShopping);
	}
}

