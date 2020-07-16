package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Item {

	WebDriver driver;
	
	private By name = By.cssSelector("div[class='inventory_details_name']");
	private By price = By.cssSelector("div[class='inventory_details_price']");
	private By add = By.cssSelector("button[class='btn_primary btn_inventory']");
	private By remove = By.cssSelector("button[class='btn_secondary btn_inventory']");
	private By back = By.cssSelector("button[class='inventory_details_back_button']");
	
	public Item(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getName() {
		return driver.findElement(name);
	}
	public WebElement getPrice() {
		return driver.findElement(price);
	}
	public WebElement getAdd() {
		return driver.findElement(add);
	}
	public WebElement getRemove() {
		return driver.findElement(remove);
	}
	public WebElement getBack() {
		return driver.findElement(back);
	}
	
}
