package com.fwlbs.mydl;


import com.fwlbs.common.AutoLogger;
import com.fwlbs.common.KeywordOfWeb;


public class MydlUserManager {

	public static void main(String[] args) {
		KeywordOfWeb kWeb=new KeywordOfWeb();
		kWeb.openBrowser("chrome");
		kWeb.visitWeb("http://c.fwlbs.com/electric/#/distribution/overview");
		kWeb.explicitlyWait("//li[@class='el-menu-item is-active']");
		kWeb.halt("2");
		//悬停在用户名上
		kWeb.hover("//i[@class='fa fa-user-circle-o mr-10']");
		kWeb.halt("1");
		AutoLogger.log.info("进入账号管理");
		kWeb.click("//ul[@class='el-dropdown-menu el-popper']/li[text()='账号管理']");
		kWeb.halt("2");
		try {
			AutoLogger.log.info("点击添加账户");
			kWeb.click("//span[text()='添加账户']");
			kWeb.halt("2");
			kWeb.input("//input[@placeholder='键入用户名']", "test8");
			kWeb.input("//input[@placeholder='键入姓名']", "王五");
			kWeb.input("//input[@placeholder='请输入部门']", "研发部");
			kWeb.input("//input[@placeholder='键入手机号码']", "19900000006");
			kWeb.click("//input[@placeholder='请选账号角色']");
			kWeb.halt("1");
			kWeb.click("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span[text()='3级角色']");
			kWeb.input("//input[@placeholder='账号密码（密码至少包含大写字母，小写字母，数字，且不少于8位）']", "Weixf123");
			kWeb.input("//input[@placeholder='确认密码（密码至少包含大写字母，小写字母，数字，且不少于8位）']", "Weixf123");
			kWeb.click("//span[text()='提交']");
			kWeb.halt("1");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			AutoLogger.log.error("添加账号失败");
		}
		//搜索
		try {
			AutoLogger.log.info("搜索刚添加成功的账号");
			kWeb.input("//input[@placeholder='输入名字或手机号搜索']", "test8");
			kWeb.click("//span[text()='搜索']");
			kWeb.halt("1");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			AutoLogger.log.error("搜索失败");
		}
		
		try {
			kWeb.click("//tbody/tr[1]/td[9]/div/div/descendant::span[text()='编辑']");
			kWeb.input("//input[@placeholder='请填入部门']", "修改后的部门");
			kWeb.click("/html/body/div[3]/div/div[3]/span/button[2]/span");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("点击编辑按钮失败");
		}
		kWeb.halt("1");
		
		kWeb.click("//tbody/tr[1]/td[9]/div/div/descendant::span[text()='删除']");
		kWeb.closeBrowser();
	}

}
