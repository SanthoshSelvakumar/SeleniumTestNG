package MyFramework;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Resources.DriverUtility;
import Resources.ExtentReportsNG;
import pageObject.HomePage;
import pageObject.ProductPage;
import pageObject.SearchResults;

public class GetSelectedItemCost 
	{	
	WebDriver driver;
	public static Logger log=LogManager.getLogger(GetSelectedItemCost.class.getName());
    @BeforeMethod
    public void getSite() throws IOException
    {
    	DriverUtility d=new DriverUtility();
        driver=d.initializeDriver();
		log.info("driver initialized");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url=d.getUrl();
        driver.get(url);
		log.info("Navigated to the URL");
    	}
    @Test(dataProvider="itemsForSearch")
    public void getItems(String item,String minPrice,String maxPrice,String brandName) throws IOException, InterruptedException
    {
        HomePage h=new HomePage(driver);
        h.searchbar().sendKeys(item);
        h.searchIcon().click();
        SearchResults s=new SearchResults(driver);
		log.info("Search results displayed");
        s.starRating().click();
		log.info("Star rating 4 and above selected");
		log.info("Brand Name Selected");
        s.Minimum().sendKeys(minPrice);
        s.Maximum().sendKeys(maxPrice);
        s.submit().click();
        s.selectitem().click();
		log.info("Price filter applied");
        Set<String> w=driver.getWindowHandles();
        Iterator<String> iT=w.iterator();
        String parentWindow=iT.next();
        String childWindow=iT.next();
        driver.switchTo().window(childWindow);
        ProductPage p=new ProductPage(driver);
		log.info("Navigated to product page");
        p.display();
		log.info("Product details displayed");
    }
    @DataProvider
    public Object[][] itemsForSearch() throws SQLException
    {
    	String host="localhost";
		String port="3306";
	Connection con=DriverManager.getConnection("jdbc:mysql://"+host +":"+port+"/seleniumlearning?autoReconnect=true&useSSL=false", "root", "Santhosh@123");
    Statement s= con.createStatement();
    ResultSet r=s.executeQuery("select * from seleniumlearning.searchitems;");
    Object[][] testData=new Object[4][4];
    int i=0;
    while(r.next())
    {
	testData[i][0]=r.getObject("name");
	testData[i][1]=r.getObject("minprice");
	testData[i][2]=r.getObject("maxprice");
	testData[i][3]=r.getObject("brand");
	i++;
    }
	return testData;
    	/*Object[][] testData=new Object[2][4];
    	testData[0][0]="redmi 8 pro";
    	testData[0][1]="10000";
    	testData[0][2]="20000";
    	testData[0][3]="redmi";
    	testData[1][0]="HP Laptops";
    	testData[1][1]="30000";
    	testData[1][2]="50000";
    	testData[1][3]="HP";
    	return testData;*/
    	 }
    @AfterMethod
    public void tearDown() throws InterruptedException
    {
    	driver.quit();
    }
}
