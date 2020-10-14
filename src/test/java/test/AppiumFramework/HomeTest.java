package test.AppiumFramework;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;

public class HomeTest extends AppTest {
	SoftAssert sa;
	HomePage h;
	
	@BeforeSuite
	public void setUp() throws IOException, InterruptedException	{
		//tAppiumInstances();
		//service=startServer();
		d = Capabilities();
	}
	
	@BeforeClass
	public void initMethod() throws InterruptedException, IOException	{
		h = new HomePage(d);
	}
	
	@Test(priority=1)
	@Parameters("homepagetitle")
	public void pageTitle(String title) throws IOException	{
		sa = new SoftAssert();
		sa.assertEquals(h.PageTitle.getText(), title);
		sa.assertAll();
	}
	
	@Test(priority=2)
	public void dropdownLabel()	{
		sa = new SoftAssert();
		sa.assertFalse(h.DropLbl.isDisplayed());
		sa.assertAll();
	}
	
	@Test(priority=3)
	@Parameters("country")
	public void selectCountry(String country)	{
		sa = new SoftAssert();
		h.DdLst.click();
		finderUtil.locateCountry(d, country).click();
		sa.assertEquals(h.Country.getText(), country);
		sa.assertAll();
	}
	
	@Test(priority=4)
	@Parameters({"defaultvalue", "namevalue"})
	public void nameField(String defval, String name)	{
		sa = new SoftAssert();
		sa.assertTrue(h.NameLbl.isDisplayed());
		sa.assertEquals(h.namefield.getText(), defval);
		h.namefield.sendKeys(name);
		sa.assertEquals(h.namefield.getText(), name);		
		sa.assertAll();
	}
	
	@Test(priority=5)
	public void genderSelect()	{
		sa = new SoftAssert();
		sa.assertTrue(h.GenderLbl.isDisplayed());
		sa.assertEquals(h.MBtn.getText(), "Male");
		sa.assertEquals(h.FBtn.getText(), "Female");
		sa.assertTrue(h.MBtn.getAttribute("checked").equals("true"));
		sa.assertAll();
	}
	
	@Test(priority=6)
	@Parameters("toastmsg")
	public void toastMsg(String msg)	{
		sa = new SoftAssert();
		String n = h.namefield.getText();
		h.namefield.clear();
		h.ShopBtn.click();
		sa.assertEquals(h.toast.getAttribute("name"), msg);
		h.namefield.sendKeys(n);
		sa.assertAll();
	}
	
	@Test(priority=7)
	@Parameters("shopbtntext")
	public void login(String btntxt) throws InterruptedException	{
		sa = new SoftAssert();
		sa.assertTrue(h.ShopBtn.isDisplayed());
		sa.assertTrue(h.ShopBtn.isEnabled());
		sa.assertEquals(h.ShopBtn.getText(), btntxt);
		h.ShopBtn.click();
		sa.assertAll();
		Thread.sleep(2000);
	}
}