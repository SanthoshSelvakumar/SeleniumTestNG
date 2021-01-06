package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage 
{
	WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="productTitle")
	WebElement title;
	@FindBy(id="priceblock_ourprice")
	WebElement price;
	@FindBy(css="td.a-span12.a-color-price.a-size-base.priceBlockSavingsString")
	WebElement discount;
	@FindBy(id="add-to-cart-button")
	WebElement addingToCart;
	
	public void display()
	{
		System.out.println("Product title: "+title.getText());
		System.out.println("Price: "+price.getText());
		System.out.println("Discount: "+discount.getText());

	}
	public WebElement addToCart()
	{
		return addingToCart;
		
	}
}
