package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	//Home Page objects	
	public HomePage(AppiumDriver driver)	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//PageTitle
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	public WebElement PageTitle;
	
	//Dropdown Label
	@AndroidFindBy(xpath="//*[@text=\"Select the country where you want to shop\"]")
	public WebElement DropLbl;
	
	//Dropdown List
	@AndroidFindBy(className="android.widget.Spinner")
	public WebElement DdLst;
	
	//Selected Country
	@AndroidFindBy(id="android:id/text1")
	public WebElement Country;
	
	//Name field label
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Your Name\"]")
	public WebElement NameLbl;
	
	//Name text field
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement namefield;
	
	//Gender field label
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Gender\"]")
	public WebElement GenderLbl;
	
	//Male radio button
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	public WebElement MBtn;
	
	//Female radio button
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	public WebElement FBtn;
	
	//Shop button
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement ShopBtn;
	
	//Toast message
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public WebElement toast;
}