
package resources;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot extends Base{

	 public static void screenShot(WebDriver driver,String path) throws Exception{
		 
	        TakesScreenshot scrShot =((TakesScreenshot)driver);
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        File DestFile=new File(path);
	        FileUtils.copyFile(SrcFile, DestFile);
	    }
	 
	 public void takeSS(String testName) throws Exception {
		 String path = System.getProperty("user.dir") +"/reports/screenshots/"+ testName + "_" + getDateTime() +".png";
		 screenShot(driver, path) ;
	 } 
}
