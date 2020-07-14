package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {

	// every initial element should be set in the global scope
	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"D://UdemyWorkspace/E2EProject/src/main/java/resources/data.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser1");

		System.out.println("~~~Test started in browser: " + browserName + "~~~");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D://Selenium_drivers/chromedriver 83/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D://Selenium_drivers/geckodriver 0.26.0/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "D://Selenium_drivers/iedriver 3.150.1/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public String getDateTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH_mm_ss");
		String dateTime = sdf.format(d).toString();
		return dateTime;
	}

	/*
	public void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	*/
}