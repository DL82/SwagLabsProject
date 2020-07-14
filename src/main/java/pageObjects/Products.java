package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Products {

	WebDriver driver;
	
	private By pageTitle = By.cssSelector("div[class='product_label']");
	private By item0 = By.cssSelector("a[id='item_0_title_link']");
	private By item1 = By.cssSelector("a[id='item_1_title_link']");
	private By item2 = By.cssSelector("a[id='item_2_title_link']");
	private By item3 = By.cssSelector("a[id='item_3_title_link']");
	private By item4 = By.cssSelector("a[id='item_4_title_link']");
	private By item5 = By.cssSelector("a[id='item_5_title_link']");
	
	public Products(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	public WebElement getItem0() {
		return driver.findElement(item0);
	}
	public WebElement getItem1() {
		return driver.findElement(item1);
	}
	public WebElement getItem2() {
		return driver.findElement(item2);
	}
	public WebElement getItem3() {
		return driver.findElement(item3);
	}
	public WebElement getItem4() {
		return driver.findElement(item4);
	}
	public WebElement getItem5() {
		return driver.findElement(item5);
	}	
}
