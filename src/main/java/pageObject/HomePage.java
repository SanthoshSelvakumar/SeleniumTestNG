package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBox;
	@FindBy(id="nav-search-submit-button")
	WebElement search;
	@FindBy(linkText="/mobile-phones/b/?ie=UTF8&node=1389401031&ref_=nav_cs_mobiles")
	WebElement mobileLink;
	@FindBy(id="nav-cart")
	WebElement cart;
	public WebElement searchbar() 
	{
		return searchBox;
	}
	public WebElement searchIcon() 
	{
		return search;
	}
	public WebElement mobiles()
	{
		return mobileLink;
		
	}
	public WebElement shoppingCart()
	{
		return cart;
		
	}
}
