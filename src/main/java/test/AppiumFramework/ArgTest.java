package test.AppiumFramework;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum ArgTest implements ServerArgument {
	CHROME_DRIVER("--chromedriver-executable");
	
	private String arg;
	
	ArgTest(String arg){
		this.arg = arg;
	}

	@Override
	public String getArgument() {
		// TODO Auto-generated method stub
		return arg;
	}
}