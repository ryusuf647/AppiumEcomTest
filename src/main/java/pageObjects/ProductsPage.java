package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	
	//Home Page objects	
	public ProductsPage(AppiumDriver driver)	{
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
	
	//Toast message
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public WebElement ToastMsg;	
	
	//Product Page Elements
	@AndroidFindBy(className="android.widget.TextView")
	public List<WebElement> PageElements;	
}