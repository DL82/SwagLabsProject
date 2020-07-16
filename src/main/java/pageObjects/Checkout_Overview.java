package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout_Overview {

WebDriver driver;

	private By pageTitle = By.cssSelector("div[class='subheader']");
	private By cancel = By.cssSelector("a[class='cart_cancel_link btn_secondary']");
	private By finish = By.cssSelector("a[class='btn_action cart_button']");
	private By paymentInfo = By.cssSelector("div[class='summary_value_label']");
	
	//Items list
	private By name = By.cssSelector("div[class='inventory_item_name']");
	private By desc = By.cssSelector("div[class='inventory_item_desc']");
	private By price = By.cssSelector("div[class='inventory_item_price']");
	
	//Summary
	private By order = By.cssSelector("div[class='summary_value_label']");
	private By total = By.cssSelector("div[class='summary_total_label']");
	private By subtotal = By.cssSelector("div[class='summary_subtotal_label']");
	private By tax = By.cssSelector("div[class='summary_tax_label']");
	

	public Checkout_Overview(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	
	public WebElement getCancel() {
		return driver.findElement(cancel);
	}
	public WebElement getFinish() {
		return driver.findElement(finish);
	}
	public List<WebElement> getName() {
		return driver.findElements(name);
	}
	public List<WebElement> getDesc() {
		return driver.findElements(desc);
	}
	public List<WebElement> getPrice() {
		return driver.findElements(price);
	}
	public WebElement getOrder() {
		return driver.findElement(order);
	}
	public WebElement getTotal() {
		return driver.findElement(total);
	}
	public WebElement getSubtotal() {
		return driver.findElement(subtotal);
	}
	public WebElement getTax() {
		return driver.findElement(tax);
	}
	public WebElement getPaymentInfo() {
		return driver.findElement(paymentInfo);
	}
	
	
	
}
