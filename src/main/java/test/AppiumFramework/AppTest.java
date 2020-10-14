package test.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.Arg;
import org.apache.http.util.Args;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

public class AppTest {
		
	public static AndroidDriver<AndroidElement> d;
	public static TouchAction t;
	public static List<String> item;
	public static String[] products;
	public static List<Double> itemprice;
	public static Double[] prices;
	public static int cart_count;	
	public static AppiumDriverLocalService service;
	
	private static DesiredCapabilities dc;
	private static FileInputStream fis;
	private static Properties prop;
	private static final String PROP_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\test\\AppiumFramework\\global.properties"; 
	
	AppTest() 	{
		try {
			fis = new FileInputStream(PROP_PATH);
			prop = new Properties();
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AppiumDriverLocalService startServer() throws InterruptedException	{
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.withArgument(ArgTest.CHROME_DRIVER, prop.getProperty("chrome_driver"));
		service = AppiumDriverLocalService.buildService(serviceBuilder);
		if (!checkIfServerIsRunning(4723))	{
			service.start();
		}
		return service;
	}
		
	public static boolean checkIfServerIsRunning(int port)	{
		boolean isRunning = false;
		ServerSocket serverSocket; 
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch(IOException e)	{
			//Server is running
			isRunning = true;
		}
		finally	{
			serverSocket = null;
		}
		return isRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+prop.getProperty("start_emu"));
		Thread.sleep(6000); //***Required to give emulator time to load in memory, otherwise won't be detected***
	}
	
	public static void stopEmulator() throws IOException	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+prop.getProperty("stop_emu"));
	}
	
	public static void tAppiumInstances() throws IOException	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+prop.getProperty("t_app_insts"));
	}
	
	public static void restartADBServer() throws IOException	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+prop.getProperty("adb_restart"));
	}
	
	public static void killADBServer() throws IOException	{
		Runtime.getRuntime().exec(prop.getProperty("t_adb"));
	}

	public static AndroidDriver<AndroidElement> Capabilities() throws IOException, InterruptedException {
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get("GeneralStoreApp"));
		dc = new DesiredCapabilities();
		String device = "emulator";//System.getProperty("deviceName");
		//dc.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.get("cap_platName"));
		//restartADBServer();
		if (device.contains("emulator"))	{
			//dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.get("emu_pv"));
			//dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emu_dn");
			restartADBServer();
			startEmulator();
		}
		//else if (((String) prop.get("device")).equalsIgnoreCase("real device"))	{
			//dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "rdev_pv");
			//dc.setCapability(MobileCapabilityType.DEVICE_NAME, "rdev_dn");
		//}
	    dc.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("cap_autoName"));
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		URL url = new URL((String) prop.get("server"));
		AndroidDriver<AndroidElement> d = new AndroidDriver<AndroidElement>(url,dc);
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		t = new TouchAction(d);
		return d;			
	}
	
	public static String getScreenCapture(String test)	{
		String path = System.getProperty("user.dir")+prop.getProperty("screencaps")+test+".jpg";
		File scrf = d.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrf, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}