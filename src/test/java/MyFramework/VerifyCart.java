package MyFramework;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Resources.DriverUtility;
import Resources.ExtentReportsNG;
import pageObject.Cart;
import pageObject.HomePage;

public class VerifyCart {
	WebDriver driver;
	public static Logger log=LogManager.getLogger(VerifyCart.class.getName());
	@BeforeTest
	public void getPage() throws IOException
	{
		DriverUtility d=new DriverUtility();
		driver=d.initializeDriver();
		log.info("driver initialized");
		String url=d.getUrl();
        driver.get(url);
        log.info("Navigated to the page");
        driver.manage().window().maximize();
	}
	
	@Test
	public void cartVerification()
	{
		HomePage h=new HomePage(driver);
		h.shoppingCart().click();
		Cart c=new Cart(driver);
		String cartMessage=c.cartItems().getText();
		log.info("Navigated to the cart");
		System.out.println(cartMessage);
		Assert.assertTrue(cartMessage.contains("empty"));
		log.info("Cart verified for zero items");
		}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	

}
