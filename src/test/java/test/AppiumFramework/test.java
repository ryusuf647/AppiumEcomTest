package test.AppiumFramework;

import java.io.IOException;

public class test extends AppTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec("taskkill -f -im node.exe");
		//Runtime.getRuntime().exec("cmd /c start \"\"" + " " + System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startAppium.bat");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startAppium.bat");
		
		//"cmd /c start \"\" build.bat"
		Thread.sleep(2000);
		Runtime.getRuntime().exec("taskkill -f -im node.exe");
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		
		//Move over to base class
	}
}