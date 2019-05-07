package com.fwlbs.mydl;

import com.fwlbs.common.KeywordOfWeb;

public class MydlKw {

	public static void main(String[] args) {
		KeywordOfWeb kWeb=new KeywordOfWeb();
		kWeb.openBrowser("chrome");
		kWeb.visitWeb("http://c.fwlbs.com/electric/#/distribution/overview");
		kWeb.explicitlyWait("//li[@class='el-menu-item is-active']");
		kWeb.halt("2");
		//悬停在用户名上
		kWeb.hover("//i[@class='fa fa-user-circle-o mr-10']");
		kWeb.halt("1");
		//点击角色管理
		kWeb.click("//ul[@class='el-dropdown-menu el-popper']/li[text()='角色管理']");
		//点击添加角色
		kWeb.halt("1");
		kWeb.click("//button[@class='el-button fr el-button--primary el-button--small is-plain']/span");
		kWeb.halt("5");
		kWeb.input("//form[@class='el-form el-form--label-right']/div[1]/div[1]/div/input", "2级管理员");
		kWeb.click("//span[text()='账号管理']");
		kWeb.click("//span[text()='日志查询']");
		kWeb.input("//input[@type='number']", "2");
		kWeb.click("//span[text()='提交']");
		kWeb.halt("2");
		kWeb.closeBrowser();

	}

}
