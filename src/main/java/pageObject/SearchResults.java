package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {
	WebDriver driver;
	public SearchResults(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".a-icon.a-icon-star-medium.a-star-medium-4")
	WebElement star;
	@FindBy(id="brandsRefinements")
	WebElement brandName;
	@FindBy(id="low-price")
	WebElement minPrice;
	@FindBy(id="high-price")
	WebElement maxPrice;
	@FindBy(css="input.a-button-input")
	WebElement go;
	@FindBy(css="div.s-include-content-margin.s-border-bottom.s-latency-cf-section")
	WebElement toSelect;
	
	public WebElement starRating()
	{
		return star;
	}
	public void brand(String brand_Name) throws InterruptedException
	{
		Thread.sleep(2000);
		brandName.findElement(By.className("a-expander-prompt")).click();
		List<WebElement>b=brandName.findElements(By.tagName("li"));
		for(WebElement bS:b)
		{System.out.println(bS.getText());
		if(bS.getText().equalsIgnoreCase(brand_Name))
		{bS.findElement(By.cssSelector(".a-icon.a-icon-checkbox")).click();;
		break;
		}
		}
	}
	public WebElement Minimum()
	{
		return minPrice;
	}
	public WebElement Maximum()
	{
		return maxPrice;
	}
	public WebElement submit()
	{
		return go;
	}
	public WebElement selectitem()
	{
		return toSelect.findElement(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal"));
	}
	
}
