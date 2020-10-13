package test.AppiumFramework;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.CartPage;

public class CartTest extends AppTest {
	SoftAssert sa;
	Double item_total;
	CartPage c;
	
	@BeforeClass
	public void initMethod() throws IOException	{
		item_total = 0.0;
		c = new CartPage(d);
	}
	
	@Test(priority=1)
	@Parameters("cartpagetitle")
	public void cartPage(String title)	{
		sa = new SoftAssert();
		sa.assertEquals(c.PageTitle.getText(), title);
		sa.assertTrue(c.BackBtn.isDisplayed()); 
		sa.assertTrue(c.BackBtn.isEnabled()); 
		sa.assertEquals(c.BackBtn.getAttribute("clickable"), "true");		
		sa.assertAll();
	}
	
	@Test(priority=2)
	@Parameters("price")
	public void validateCartItems(String pcheck)	{
		int i;
		Product prod;
		sa = new SoftAssert();
		i = 0;
		
		for(; i < products.length ;)	{
			prod = new Product(finderUtil.getCartItem(d, i));							
			sa.assertEquals(prod.getName(), products[i]);
			sa.assertEquals(prod.getprice(), "$" + Double.toString(prices[i]));
			if(!prod.getprice().equals(pcheck))	{
				item_total += Double.parseDouble(prod.getprice().substring(1));
			}
			i++;
		}
		sa.assertAll();
	}	
	
	@Test(priority=3)
	public void validateCartTotal()	{
		sa = new SoftAssert();
		item_total = Math.round(item_total * 100.0) / 100.0;		
		sa.assertEquals(finderUtil.getCartTotal(d), item_total);
		sa.assertAll();
	}
	
	@Test(priority=4)
	@Parameters("chkbtxt")
	public void validateChkBox(String txt)	{
		sa = new SoftAssert();
		sa.assertEquals(finderUtil.getChkBox(d).getText(), txt);
		finderUtil.getChkBox(d).click();
		sa.assertEquals(finderUtil.getChkBox(d).getAttribute("checked"), "true");
		sa.assertAll();
	}
	
	@Test(priority=5)
	@Parameters({"tctxt", "tctitle", "tcbody1", "tcbody2", "tcbody3", "tcClose"})
	public void validateTC(String tctxt, String tctitle, String tcb1, String tcb2, String tcb3, String tcClose)	{
		sa = new SoftAssert();
		sa.assertEquals(finderUtil.getTC(d).getText(), tctxt);
		t.longPress(longPressOptions().withElement(element(finderUtil.getTC(d))).withDuration(ofSeconds(2))).release().perform();
		sa.assertEquals(finderUtil.getAlertTitle(d).getText(), tctitle);
		sa.assertEquals(finderUtil.getAlertBody(d).getText(), tcb1+tcb2+tcb3);
		sa.assertEquals(finderUtil.getAlertClose(d).getText(), tcClose);
		finderUtil.getAlertClose(d).click();
		sa.assertAll();
	}
	
	@Test(priority=6)
	@Parameters("purchasetxt")
	public void validatePurchaseBtn(String btntxt) throws InterruptedException	{
		sa = new SoftAssert();
		sa.assertEquals(finderUtil.getPurchaseBtn(d).getText(), btntxt);
		finderUtil.getPurchaseBtn(d).click();
		Thread.sleep(2000);
		sa.assertAll();
	}
}