package com.fwlbs.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleDriver {
	public WebDriver driver=null;
	public GoogleDriver(String driverPath) {
		//设置chorme路径
		System.setProperty("webdriver.chrome.driver",driverPath);
		ChromeOptions option=new ChromeOptions();
		//去除黄色警告
		option.addArguments("--disable-infobars");
		//加载chrome用户文件
		option.addArguments("--user-data-dir=C:\\Users\\pc\\AppData\\Local\\Google\\Chrome\\User Data");
		//最大化浏览器窗口
		option.addArguments("--start-maximized");
		//白名单设置
		option.addArguments("--whitelisted-ips=\"\"");
		
		//创建一个chromeDriver 的接口，用来连接chrome
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY,option);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability(CapabilityType.VERSION, "");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		
		//创建一个chorme实例
		try {
			this.driver=new ChromeDriver(option);
			//访问空白页
//			driver.get("about:blank");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("log--error：创建driver失败！！");
		}
	
	}
	public WebDriver getdriver() {
		return this.driver;
	}

}
