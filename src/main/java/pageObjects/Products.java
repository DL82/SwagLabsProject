package pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Products {

	WebDriver driver;

	public Products(WebDriver driver) {
		this.driver = driver;
	}

	private By pageTitle = By.cssSelector("div[class='product_label']");

	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}

	private By select = By.cssSelector("select[class='product_sort_container']");

	public WebElement getSelect() {
		return driver.findElement(select);
	}

	private By name = By.cssSelector("div[class='inventory_item_name']");

	public List<WebElement> getName() {
		return driver.findElements(name);
	}

	private By price = By.cssSelector("div[class='inventory_item_price']");

	public List<WebElement> getPrice() {
		return driver.findElements(price);
	}

	private By add = By.cssSelector("button[class='btn_primary btn_inventory']");

	public List<WebElement> getAdd() {
		return driver.findElements(add);
	}

	private By remove = By.cssSelector("button[class='btn_secondary btn_inventory']");

	public List<WebElement> getRemove() {
		return driver.findElements(remove);
	}

	private By back = By.cssSelector("button[class='inventory_details_back_button']");

	public WebElement getBack() {
		return driver.findElement(back);
	}

	public void clickAdd() {
		for (WebElement elem : getAdd()) {
			elem.click();
		}
	}

	public void addFirstItem() throws InterruptedException {
		for (WebElement addButton : getAdd()) {
			if (addButton.isDisplayed()) {
				getAdd().get(0).click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	public void removeFirstItem() throws InterruptedException {
		getRemove().get(0).click();
		Thread.sleep(1000);
	}

	public void orderNameDesc() throws InterruptedException {
		Select order = new Select(getSelect());
		order.selectByValue("za");
		Thread.sleep(1000);
		System.out.println("Current order: " + order.getFirstSelectedOption().getText());
	}

	public void orderPriceAsc() throws InterruptedException {
		Select order = new Select(getSelect());
		order.selectByValue("lohi");
		Thread.sleep(1000);
		System.out.println("Current order: " + order.getFirstSelectedOption().getText());
	}

	public void orderPriceDesc() throws InterruptedException {
		Select order = new Select(getSelect());
		order.selectByValue("hilo");
		Thread.sleep(1000);
		System.out.println("Current order: " + order.getFirstSelectedOption().getText());
	}

	public void orderNameAsc() throws InterruptedException {
		Select order = new Select(getSelect());
		order.selectByValue("az");
		Thread.sleep(1000);
		System.out.println("Current order: " + order.getFirstSelectedOption().getText());

	}

	public String getFirstItem() {
		List<WebElement> list = getName();
		List<String> strings = new ArrayList<String>();
		for (WebElement element : list) {
			strings.add(element.getText());
		}
		System.out.println(strings);
		return strings.get(0);
	}

	public String getFirstPrice() {
		List<WebElement> list = getPrice();
		List<String> strings = new ArrayList<String>();
		for (WebElement element : list) {
			strings.add(element.getText());
		}
		System.out.println(strings);
		return strings.get(0);
	}	
	
	public WebElement getRandom() {
		int i = (int) Math.floor(Math.random() * getName().size());
		return getName().get(i);
	}

	public void addAllItems() {
		for (WebElement add : getAdd()) {
			add.click();
		}
	}

}
