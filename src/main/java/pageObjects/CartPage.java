package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {
	
	//Home Page objects	
	public CartPage(AppiumDriver driver)	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//PageTitle
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	public WebElement PageTitle;
	
	//Add To Cart Button
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement CartBtn;
	
	//Back Button
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_back")
	public WebElement BackBtn;
}