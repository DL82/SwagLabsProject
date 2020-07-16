package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {

	WebDriver driver;
	
	private By title = By.cssSelector("div[class='subheader']");
	private By checkout = By.cssSelector("a[class='btn_action checkout_button']");
	private By remove = By.cssSelector("a[class='btn_secondary cart_button']");
	private By name = By.cssSelector("div[class='inventory_item_name']");
	private By price = By.cssSelector("div[class='inventory_item_price']");
	private By continueShopping = By.cssSelector("a[class='btn_secondary']");
	private By itemBlock = By.cssSelector("div[class='cart_item']");
	
	public Cart(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	public WebElement getCheckout() {
		return driver.findElement(checkout);
	}
	public List<WebElement> getRemove() {
		return driver.findElements(remove);
	}
	public List<WebElement> getName() {
		return driver.findElements(name);
	}
	public List<WebElement> getPrice() {
		return driver.findElements(price);
	}
	public WebElement getContinueShopping() {
		return driver.findElement(continueShopping);
	}
	
	public List<WebElement> getItemBlock(){
		return driver.findElements(itemBlock);
	}
	
	public void removeItems() {
		for(int i = 1; i < getRemove().size()-1; i++) {
				getRemove().get(i).click();
		}
	}
}

