package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtility 
{
	Properties prop;
	int i=0;
    public WebDriver initializeDriver() throws IOException
    {
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data.properties");
    	prop.load(fis);
    	//String browserName=prop.getProperty("browser");
    	String browserName=System.getProperty("browser");
    	WebDriver driver;
    	if(browserName.contains("Chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
    		ChromeOptions options=new ChromeOptions();
    		if(browserName.contains("headless"))
    		options.addArguments("--headless");
    		driver=new ChromeDriver(options);
    	}
    	else
    	{
    		System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\resources\\msedgedriver.exe");
    		driver=new EdgeDriver();
    	}
		return driver;
    	
    }
    public String getUrl()
    {
    	return prop.getProperty("url");
    }
    public String getScreenshot(String testCaseName,WebDriver driver)
    {i++;
    	TakesScreenshot ss=(TakesScreenshot) driver;
    	File src=ss.getScreenshotAs(OutputType.FILE);
    	String fileDestination=System.getProperty("user.dir")+"\\reports\\"+testCaseName+i+".png";
    	try {
			FileUtils.copyFile(src, new File(fileDestination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fileDestination;
    }
}
