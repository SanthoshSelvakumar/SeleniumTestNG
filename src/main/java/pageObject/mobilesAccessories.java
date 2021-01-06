package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mobilesAccessories {
	WebDriver driver;
	public mobilesAccessories(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="gwd-browseMultiCategoryCard-merchandised-search-6")
	WebElement launches ;
	
	public void getNewLaunches()
	{
		
	}

}
