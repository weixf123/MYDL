package com.fwlbs.common;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IEDriver { // IE浏览器驱动类
	public WebDriver driver;
	public InternetExplorerDriverService service = null;

	public IEDriver(String driverpath) {
		// 设置 IE 的路径
		System.setProperty("webdriver.ie.driver", driverpath);
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		//设置忽略安全校验
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		try {
			// 创建一个 IE 的浏览器实例
			this.driver = new RemoteWebDriver(service.getUrl(), ieCapabilities);
			// 让浏览器访问空白页面
			driver.get("about:blank");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log--error：创建driver失败！！");
		}
	}

	public WebDriver getdriver() {
		return this.driver;
	}
	
	public void close() {
		driver.quit();
		service.stop();
	}
}