package com.fwlbs.mydlinterTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fwlbs.mydlinter.ExcelWriter;

import javafx.scene.chart.PieChart.Data;

public class ExcelWriteTest {

	public static void main(String[] args) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String creatdate=sdf.format(date);
		//通过用例表复制内容到结果表中
		ExcelWriter excelW=new ExcelWriter("cases/SKDSJLogin.xlsx", "cases/Res-"+creatdate+"SKDSJLogin.xlsx");
		excelW.writeCell(0, 12, "测试输入");
		excelW.writeFailCell(1, 12, "测试输入失败");
		excelW.save();
		

	}

}
