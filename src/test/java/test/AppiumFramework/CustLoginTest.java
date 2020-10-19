//Data-Driven test using MQSQL database

package test.AppiumFramework;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.HomePage;

public class CustLoginTest extends AppTest {
	SoftAssert sa;
	HomePage h;
	
	@BeforeClass
	public void initMethod() throws InterruptedException, IOException	{
		h = new HomePage(d);
	}
	
	@Test(dataProvider="MySQL-provider")
	public void mySQL_Test(String country, String name, String gender) throws InterruptedException	{
		sa = new SoftAssert();
		boolean hasName = false;
		
		//Country select
		h.DdLst.click();
		finderUtil.locateCountry(d, country).click();
		sa.assertEquals(h.Country.getText(), country); 
		
		//Enter name
		h.namefield.sendKeys(name);
		
		if(!(name.length() > 0))	{
			sa.assertEquals(h.namefield.getText(), "Enter name here");
		}
		else	{
			sa.assertEquals(h.namefield.getText(), name);
			hasName = true;
		}
		
		//Select gender
		if(h.MBtn.getText().equals(gender))	{
			h.MBtn.click();
			sa.assertTrue(h.MBtn.getAttribute("checked").equals("true"));
		}
		else	{
			h.FBtn.click();
			sa.assertTrue(h.FBtn.getAttribute("checked").equals("true"));
		}
		
		//Click shop button
		h.ShopBtn.click();
		
		if (hasName)	{
			Thread.sleep(2000);
			d.pressKey(new KeyEvent(AndroidKey.BACK));
		}
		else	{
			//validate toast message
			sa.assertEquals(h.toast.getAttribute("name"), "Please enter your name");		
		}
		Thread.sleep(2000);
		resetFields();		
		sa.assertAll();
	}
	
	public void resetFields()	{
		//Reset country
		h.DdLst.click();
		finderUtil.scrollToTop(d);
		finderUtil.locateCountry(d, "Afghanistan").click();
		
		//Reset name
		h.namefield.clear();
		
		//Reset gender
		h.MBtn.click();
	}
	
	@DataProvider(name = "MySQL-provider")
	public String[][] mySQL_Data() throws SQLException, ClassNotFoundException	{
		int rowCount = 0;
		int columnCount = 0;
		String host = "localhost";
		String port = "3306";
		String myData [][] = null;
		
		try	{
			//Connect to the database
			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/custdata", "root", "*_r3@dyAP1!_*");
			
			//Execute a SQL query
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//Note: When initializing the Statement variable, the above parameters need to be passed as arguments
			//This prevents the resultset from losing the data while performing iterations on it (ie., rs.next, rs.previous ....)
			
			ResultSet rs = s.executeQuery("select * from customer");
			rs.last();
			rowCount = rs.getRow();
			
			//Get number of columns, but return only the first three
			//which include country, name, and gender
			ResultSetMetaData rs_md = rs.getMetaData();
			columnCount = rs_md.getColumnCount() - 1;		
			
			//Initialize the data structure
			myData = new String[rowCount][columnCount];			
					
			//populate data structure
			int i = 0;
			rs.beforeFirst();
			
			while(rs.next())	{
				for(int j = 0; j < columnCount; j++)	{
					myData[i][j] = rs.getString(j+1);
				}
				i++;
			}			
			s.close();
			con.close();
		}
		catch(Exception e)	{}		
		return myData;
	}
	
	@AfterSuite
	public void tearDown() throws IOException	{
		d.quit();
		stopEmulator();
		killADBServer();
		service.stop();		
	}
}