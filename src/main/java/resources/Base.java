package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {

	// every initial element should be set in the global scope
	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"/src/main/java/resources/data.properties");
				 //"D://UdemyWorkspace/E2EProject/src/main/java/resources/data.properties"
		prop.load(fis);
		
		String browserName = System.getProperty("browser"); /** Running from Jenkins **/
		//String browserName = prop.getProperty("browser"); /** Running from Eclipse **/
		String driverPath = System.getProperty("user.dir") +"/drivers/";
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("head")) {
				options.addArguments("headless");
			}
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe"); //"D://Selenium_drivers/chromedriver 83/chromedriver.exe"
			driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe"); // "D://Selenium_drivers/geckodriver 0.26.0/geckodriver.exe"
			driver = new FirefoxDriver();

		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe"); //"D://Selenium_drivers/iedriver 3.150.1/IEDriverServer.exe"
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static String getDateTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH'h'mm'm'");
		String dateTime = sdf.format(d).toString();
		return dateTime;
	}
	
	public void addText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void getAtt(WebElement element,String attribute) {
		element.getAttribute(attribute);
	}
	
	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}	
}