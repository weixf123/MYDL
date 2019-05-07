package com.fwlbs.mydl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromDriverTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		options.addArguments("--user-data-dir=C:\\Users\\pc\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("--start-maximized");
		options.addArguments("--whitelisted-ips=\"\"");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://c.fwlbs.com/electric/#/distribution/overview");
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		// 设置等待的预期条件为，元素可以被定位到
		ewait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("//li[@class='el-menu-item is-active']"));

			}
		});
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
