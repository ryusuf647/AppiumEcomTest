package test.AppiumFramework;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class finderUtil extends AppTest {
	
	public static WebElement locateCountry(AndroidDriver<AndroidElement> d, String c)	{		
		return d.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + c + "\"));");
	}
	
	public static boolean locateProduct(AndroidDriver<AndroidElement> d, String item) throws InterruptedException	{
		String s; 
		boolean e_found;
		e_found = true;		
		
		s = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + item + "\"))";
		
		//Locate element
		try	{
			d.findElement(MobileBy.AndroidUIAutomator(s));
		}	catch(NoSuchElementException e)	{
				e_found = false;
		}
		
		return e_found;
	}
	
	public static Double getPrice(AndroidDriver<AndroidElement> d, String item, int j)	{
		Double p;
		p = 0.0;
		
		try	{
			p = Double.parseDouble(d.findElements(By.className(item)).get(j+1).getText().substring(1));
		}	catch (NullPointerException e) {
				p=null;
		}
		
		return p;
	}
	
	public static WebElement getAddToCart(AndroidDriver<AndroidElement> d, String element, int j, String item) {
		boolean found;
		String text;
		WebElement w;
		
		w = null;
		found = true;
		text = "null";
		
		try	{
			text = d.findElements(By.className(element)).get(j+2).getText();
		}
		catch(NoSuchElementException e)	{
			found = false;
		}
			
		if (found) {
			if(text.equalsIgnoreCase("add to cart"))	{
				w = d.findElements(By.className(element)).get(j+2);             
			}
		}		
		return w;
	}
	
	public static int getCartCount(AndroidDriver<AndroidElement> d)	{
		return Integer.parseInt(d.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText());
	}
	
	public static Product getCartItem(AndroidDriver<AndroidElement> d, int i)	{
		Product aProduct = new Product();
		boolean found;
		
		found = false;
		
		try	{
			aProduct.setName(d.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"" + products[i] + "\")").getText());
			found = true;				
			}
			catch(NoSuchElementException e1) {	
				try {
					d.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(30)"));									  
				}
				catch(InvalidSelectorException ise)	{
					try	{
						aProduct.setName(d.findElement(By.xpath("//*[@text=\"" + products[i] + "\"]")).getText());
						found = true;
					}
					catch(NoSuchElementException e2)	{}
				}	
			}
			
			if(found)	{
				try {
					aProduct.setprice(d.findElement(By.xpath("//*[@text=\"$" + Double.toString(prices[i]) + "\"]")).getText());
					}
				catch(NoSuchElementException e)	{}
				}
		
		return aProduct;
	}
	
	public static double getCartTotal(AndroidDriver<AndroidElement> d)	{
		return Math.round(Double.parseDouble(d.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2)) * 100.0) / 100.0;
	}
	
	public static WebElement getChkBox(AndroidDriver<AndroidElement> d)	{
		WebElement chbx;
		
		try	{
			chbx = d.findElement(By.className("android.widget.CheckBox"));
		}
		catch(NoSuchElementException e)	{
			chbx = null;
		}		
		return chbx;
	}
	
	public static WebElement getTC(AndroidDriver<AndroidElement> d)	{
		WebElement tc;
		
		try	{
			tc = d.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		}
		catch(NoSuchElementException e)	{
			tc = null;
		}			
		return tc;	
	}
	
	public static WebElement getAlertTitle(AndroidDriver<AndroidElement> d)	{
		WebElement title;
		
		try	{
			title = d.findElement(By.id("com.androidsample.generalstore:id/alertTitle"));
		}
		catch(NoSuchElementException e)	{
			title = null;
		}
		return title;
	}
	
	public static WebElement getAlertBody(AndroidDriver<AndroidElement> d)	{
		WebElement body;
		
		try	{
			body = d.findElement(By.id("android:id/message"));
		}
		catch(NoSuchElementException e)	{
			body = null;
		}		
		return body;
	}
	
	public static WebElement getAlertClose(AndroidDriver<AndroidElement> d)	{
		WebElement close;
		try	{
			close = d.findElement(By.id("android:id/button1"));
		}
		catch(NoSuchElementException e)	{
			close = null;
		}
		return close;
	}
	
	public static WebElement getPurchaseBtn(AndroidDriver<AndroidElement> d)	{
		WebElement btn;
		
		try	{
			btn = d.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
		}
		catch(NoSuchElementException e)	{
			btn = null;
		}		
		return btn;	
	}
	
	public static String switchContext(AndroidDriver<AndroidElement> d, String view) throws InterruptedException	{
		String c = null;
		Set<String> context = null;
		
		context = d.getContextHandles();
		
		for(String contextName : context)
		{
			if(contextName.toLowerCase().contains(view))	{
				c = contextName;
			}
		}			
		return c;
	}
	
	public static void scrollToTop(AndroidDriver<AndroidElement> d)	{
		try {
			d.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(30)"));
			}
		catch(InvalidSelectorException e)	{}
	}
}