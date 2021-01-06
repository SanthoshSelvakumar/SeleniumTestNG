package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
	WebDriver driver;
	public Cart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class=\"a-column a-span8 a-span-last\"]/div[1]")
	private WebElement cartStatus ;
	
	public WebElement cartItems()
	{
		return cartStatus;
	}
	}
