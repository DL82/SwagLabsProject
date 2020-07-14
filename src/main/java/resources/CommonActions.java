package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonActions {

	WebDriver driver;

	public CommonActions(WebDriver driver) {
		this.driver = driver;
	}

	public void addText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void elemAtt(WebElement element,String attribute) {
		element.getAttribute(attribute);
	}
	
	public String elemText(WebElement element) {
		String text = element.getText();
		return text;
	}
		
}
