package com.fwlbs.mydlinterTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fwlbs.mydlinter.ExcelReader;
import com.fwlbs.mydlinter.ExcelWriter;
import com.fwlbs.mydlinter.KeywordOfInter;

public class SkdsjExcelTest {

	public static void main(String[] args) {
		KeywordOfInter key = new KeywordOfInter();
		// 读取excel内容
		ExcelReader excelR = new ExcelReader("cases/SKDSJLogin.xlsx");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String creatdate=sdf.format(date);
		//通过用例表复制内容到结果表中
		ExcelWriter excelW=new ExcelWriter("cases/SKDSJLogin.xlsx", "cases/Res-"+creatdate+"SKDSJLogin.xlsx");
		// 存储每一行的数据
		List<String> allLine = null;
		// 循环遍历所有的sheet页
		for (int sNo = 0; sNo < excelR.getTotalSheetNo(); sNo++) {
			excelR.useSheetByIndex(sNo);
			// 循环sheet页所有行
			for (int currentLine = 0; currentLine < excelR.rows; currentLine++) {
				allLine = excelR.readLine(currentLine);
				System.out.println(allLine);
				// 执行读出的数据所对应的脚本
				if (allLine.get(0).equals("") && allLine.get(1).equals("")) {
					// 第一步，执行关键字的方法
					switch (allLine.get(3)) {
					case "addHeader":
						key.addHeader(allLine.get(4));
						excelW.writeCell(currentLine, 10, "PASS");
						break;
					case "testPost":
						String response=key.testPost(allLine.get(4), allLine.get(5));
						excelW.writeCell(currentLine, 10, response);
						break;

					case "testPostJson":
						String response1=key.testPostJson(allLine.get(4), allLine.get(5));
						excelW.writeCell(currentLine, 11, response1);
						break;
					case "saveParam":
						key.saveParam(allLine.get(4), allLine.get(5));
						excelW.writeCell(currentLine, 10, "PASS");
						break;

					case "testGet":
						key.testGet(allLine.get(4), allLine.get(5));
						break;

					}
					// 第二步，执行校验列对应的断言
					switch (allLine.get(7)) {
					case "equal":
						Boolean result=key.assertSame(allLine.get(9), allLine.get(8));
						if(result) {
							excelW.writeCell(currentLine, 10, "PASS");
						}else {
							excelW.writeFailCell(currentLine, 10, "FAIL");
						}
						break;

					case "contains":
						key.assertContains(allLine.get(9), allLine.get(8));
						break;
					}
				}
			}
			
		}
		excelW.save();
	}

}
