package com.fwlbs.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeywordOfWeb {
	// 成员变量webdrvier对象driver，用这个成员变量操作后续的所有方法。
	public WebDriver driver = null;

	public KeywordOfWeb() {

	}

	/**
	 * 启动浏览器的方法
	 * 
	 * @param浏览器的类型：可以是IE/FF/chrome
	 */
	public void openBrowser(String browserType) {
		try {
			switch (browserType) {
			case "chrome":
				GoogleDriver gg = new GoogleDriver("tools/chromedriver.exe");
				driver = gg.getdriver();
				AutoLogger.log.info("启动chrome浏览器");
				break;
			case "FF":
				FFDriver ff = new FFDriver("E:\\Program Files\\Mozilla Firefox\\firefox.exe", "tools/geckodriver.exe");
				driver = ff.getdriver();
				AutoLogger.log.info("启动Firefox浏览器");
				break;
			case "IE":
				IEDriver IE = new IEDriver("tools/IEDriver.exe");
				driver = IE.getdriver();
				AutoLogger.log.info("启动IE浏览器");
				break;
			default:
				GoogleDriver google = new GoogleDriver("tools/chromedriver.exe");
				driver = google.getdriver();
				AutoLogger.log.info("默认启动chrome浏览器");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("启动浏览器失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}

	}

	/**
	 * 调整浏览器窗口大小
	 */
	public void setWindow() {
		Point p = new Point(320, 0);
		Dimension d = new Dimension(1400, 1000);
		driver.manage().window().setPosition(p);
		driver.manage().window().setSize(d);
		AutoLogger.log.info("调整浏览器窗口大小");
	}
	
	
	//报错时截图
		public void saveScrShot(String method) {
			//获取当前的执行时间
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
			//当前时间的字符串
			String createdate=sdf.format(date);
			//拼接文件名，形式为：工作目录路径+方法名+执行时间+.png
			String srcName="SCRshort/"+method+createdate+".png";
			//以当前文件名创建文件
			File srcShot=new File(srcName);
			//将截图保存到临时文件
			File tmp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(tmp, srcShot);
				AutoLogger.log.info("报错时截图");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AutoLogger.log.error(e,e.fillInStackTrace());
			}
			
		}


	/**
	 * 访问URL的方法
	 * 
	 * @param参数为网页URL
	 */
	public void visitWeb(String URL) {
		try {
			driver.get(URL);
			AutoLogger.log.info("访问url"+URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("访问URL失败");
		}
	}

	/**
	 * 定位输入框并输入内容方法
	 * 
	 * @param 定位的表达式以及输入的字符串
	 */
	public void input(String xpath, String content) {
		try {
			explicitlyWait(xpath);
			WebElement element = driver.findElement(By.xpath(xpath));
			element.clear();
			element.sendKeys(content);
			AutoLogger.log.info("输入"+content+"内容");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("输入内容失败");
		}
	}

	public void click(String xpath) {
		try {
			explicitlyWait(xpath);
			WebElement element = driver.findElement(By.xpath(xpath));
			element.click();
			AutoLogger.log.info("点击"+xpath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("点击失败");
		}
	}

	/**
	 * 使用actions类，调用moveElement方法实现悬停。
	 * 
	 * @param xpath
	 */
	public void hover(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}

	/**
	 * 实现显式等待的方法，在每次定位元素时，先尝试找元素，给10秒钟的最长等待。
	 */
	public void explicitlyWait(String xpath) {
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 10);
			// 设置等待的预期条件为，元素可以被定位到。
			ewait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTitle() {
		String title = "";
		try {
			title = driver.getTitle();
			return title;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void implicitlyWait() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 强制等待的方法
	 * 
	 * @param t强制等待的秒数，用字符串类型传递
	 */
	public void halt(String t) {
		try {
			int time = Integer.parseInt(t);
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 进入iframe子页面
	public void intoIframe(String xpath) {
		try {
			explicitlyWait(xpath);
			WebElement frame = driver.findElement(By.xpath(xpath));
			driver.switchTo().frame(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("进入iframe失败！");
			e.printStackTrace();
		}
	}

	// 退出子页面
	public void outIframe() {

		try {
			// 切回主页面
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 通过窗口标题切换窗口
	public void switchWindow(String target) {
		// 创建一个字符串便于之后存放句柄
		String s = null;
		// 获取当前页面中的句柄
		Set<String> handles = driver.getWindowHandles();
		// 循环尝试，找到目标浏览器页面的句柄
		for (String t : handles) {
			// 遍历每一个句柄，判断窗口的标题是否包含预期字符
//				System.out.println(t);
			if (driver.switchTo().window(t).getTitle().equals(target)) {
				s = t;
			}
		}
		// 切换到目标句柄的页面中
		try {
			driver.switchTo().window(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭旧窗口，切换到新窗口操作
	public void closeOldWin() {
		List<String> handlelist = new ArrayList<String>();
		// 返回一个句柄集合
		Set<String> handles = driver.getWindowHandles();
		// 循环获取集合里面的句柄，保存到List数组handles里面
		Iterator<String> it = handles.iterator();
		while(it.hasNext()) {
			handlelist.add(it.next().toString());
		}
		// 关闭第一个窗口
		driver.close();
		// 切换到新窗口
		try {
			driver.switchTo().window(handlelist.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭新窗口
	public void closeNewWin() {
		List<String> handles = new ArrayList<String>();
		Set<String> s = driver.getWindowHandles();
		// 循环获取集合里面的句柄，保存到List数组handles里面
		for (Iterator<String> it = s.iterator(); it.hasNext();) {
			handles.add(it.next().toString());
		}
		try {
			driver.switchTo().window(handles.get(1));
			driver.close();
			driver.switchTo().window(handles.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取js运行结果
	public String getJs(String text) {
		String t = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			t = js.executeScript(text).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	// 执行无返回的js脚本
	public void runJs(String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 执行浏览器滚动
	public void scrollWindowStraight(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String jsCmd = "window.scrollTo(0," + height + ")";
			js.executeScript(jsCmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 实现select方法
	public void selectByText(String xpath, String text) {
		try {
			// 将webelement转换为select
			Select userSelect = new Select(driver.findElement(By.xpath(xpath)));
			userSelect.selectByVisibleText(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//封装关键字实现登录
	public void login() {
		visitWeb("http://112.74.191.10:8000/");
		click("//a[@class='red']");
		input("//input[@id='username']", "2798145476@qq.com");
		input("//input[@id='password']", "qinghong");
		input("//input[@placeholder='验证码']", "1");
		click("//a[@name='sbtbutton']");
	}
}
