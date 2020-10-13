package test.AppiumFramework;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.ProductsPage;

public class ProductsTest extends AppTest {
	SoftAssert sa;
	ProductsPage p;
	
	@BeforeClass
	public void initMethod() throws IOException	{
		p = new ProductsPage(d);
		item = new ArrayList<String>();
		itemprice = new ArrayList<Double>();
		//item.add("Nike SFB Jungle");
		//item.add("Jordan Lift Off");
		//item.add("LeBron Soldier 12 ");
		//item.add("Air Jordan 9 Retro");
		//item.add("Air Jordan 1 Mid SE");
		//item.add("Jordan 6 Rings");
		item.add("Converse All Star");
		
		products = item.toArray(new String[item.size()]);
		cart_count = 0;
	}
	
	@Test(priority=1)
	@Parameters("productspagetitle")
	public void productsPage(String title)	{
		sa = new SoftAssert();
		sa.assertEquals(p.PageTitle.getText(), title);
		sa.assertTrue(p.CartBtn.isDisplayed());
		sa.assertTrue(p.CartBtn.isEnabled());
		sa.assertEquals(p.CartBtn.getAttribute("clickable"), "true");
		sa.assertTrue(p.BackBtn.isDisplayed());
		sa.assertTrue(p.BackBtn.isEnabled());
		sa.assertEquals(p.BackBtn.getAttribute("clickable"), "true");		
		sa.assertAll();
	}
	
	@Test(priority=2)
	@Parameters("toastmsg")
	public void toastMsg(String msg)	{
		sa = new SoftAssert();
		p.CartBtn.click();
		sa.assertEquals(p.ToastMsg.getAttribute("name"), "cheers!");
		sa.assertAll();
	}
	
	@Test(priority=3)
	@Parameters({"pagelms", "btnadded"})
	public void locateItems(String elements, String addedtxt) throws InterruptedException	{
		String text;
		int count = 0;
		Double price = 0.0;
		WebElement addbtn;
		
		sa = new SoftAssert();
		
		for(int i = 0; i < products.length; i++) {
			boolean found = finderUtil.locateProduct(d, products[i]);
			sa.assertEquals(found, true); //assert that the item was found
			if(found)	{
				count = p.PageElements.size();
				for(int j = 0; j < count; j++) {
					text = p.PageElements.get(j).getText();
					if(text.equals(products[i]))	{
						price = finderUtil.getPrice(d, elements, j);
						boolean p = price > 0.0 && price != null;
						sa.assertEquals(p, true); //assert that the price was found
						if(p)	{
							itemprice.add(price);
						}
						addbtn = finderUtil.getAddToCart(d, elements, j, products[i]);
						boolean b = addbtn != null;
						sa.assertEquals(b, true); //assert that add to cart was found
						if(b)	{
							addbtn.click();
							sa.assertEquals(addbtn.getText(), addedtxt);
							cart_count++;
						}
						break;
					}
				}
				finderUtil.scrollToTop(d);
			}
		}
		sa.assertEquals(finderUtil.getCartCount(d), cart_count);
		sa.assertAll();
	}
	
	@Test(priority=4)
	public void gotoCart() throws InterruptedException	{
		p.CartBtn.click();
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void convertFromPriceList()	{
		prices = itemprice.toArray(new Double[itemprice.size()]);
	}
}