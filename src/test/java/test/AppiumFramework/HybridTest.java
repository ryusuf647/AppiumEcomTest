package test.AppiumFramework;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HybridTest extends AppTest {
	SoftAssert sa;
	
	@Test(priority=1)
	public void webView() throws InterruptedException	{
		String web_ctxt;
		sa = new SoftAssert();	
		web_ctxt = finderUtil.switchContext(d, "webview");		
		sa.assertTrue(web_ctxt.toLowerCase().contains("webview"));
		
		if(web_ctxt.toLowerCase().contains("webview"))	{
			d.context(web_ctxt);
			sa.assertTrue(d.getContext().toLowerCase().contains("webview"));
			d.findElement(By.name("q")).sendKeys("youtube");
			d.findElement(By.name("q")).sendKeys(Keys.ENTER);
		}
		else	{
			System.out.println("Could not locate webview.");
		}
		sa.assertAll();
	}
	
	@Test(priority=2)
	public void nativeAppView() throws InterruptedException	{
		String na_ctxt;
		sa = new SoftAssert();
		d.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000);
		na_ctxt = finderUtil.switchContext(d, "native_app");		
		sa.assertTrue(na_ctxt.toLowerCase().contains("native_app"));
				
		if(na_ctxt.toLowerCase().contains("native_app"))	{
			d.pressKey(new KeyEvent(AndroidKey.BACK));
			d.context(na_ctxt);
			sa.assertTrue(d.getContext().toLowerCase().contains("native_app"));
		}
		else	{
			System.out.println("Could not locate native app view.");
		}
		sa.assertAll();
	}
	
	@AfterSuite
	public void tearDown() throws IOException	{
		d.quit();
		//stopEmulator();
		//killADBServer();
		//service.stop();
	}
}