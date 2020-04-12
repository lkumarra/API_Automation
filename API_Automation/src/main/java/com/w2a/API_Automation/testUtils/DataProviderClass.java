package com.w2a.API_Automation.testUtils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.API_Automation.setUp.APISetUp;

public class DataProviderClass extends APISetUp {

//	@DataProvider(name = "dp")
//	public Object[][] getData(Method m) {
//		String sheetName = m.getName();
//
//		int rows = excel.getRowCount(sheetName);
//		int cols = excel.getColumnCount(sheetName);
//
//		Object[][] data = new Object[rows - 1][1];
//
//		Hashtable<String, String> table;
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//			table = new Hashtable<String, String>();
//			for (int colNum = 0; colNum < cols; colNum++) {
//				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
//				data[rowNum - 2][0] = table;
//			}
//		}
//		return data;
//	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		System.out.println(configProperty);
		System.out.println("SheetName-->" + configProperty.getTestDataSheetName());
		String sheetName = configProperty.getTestDataSheetName();
		int rows = excel.getRowCount(sheetName);// 100
		String testName = m.getName();
		int testCaseRowNum = 1;
		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
			String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);
			// System.out.println("TestCase name in excel-->"+testCaseName);
			if (testCaseName.equalsIgnoreCase(testName))
				break;
		} // Checking total rows in test case
		System.out.println("TestCase starts from:- " + testCaseRowNum);
		int dataStartRowNum = testCaseRowNum + 2;// dataStartRowNum=3
		int testRows = 0;
		while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("endOfTestData")) {
			testRows++;// 1
		}
		int colStartColNum = testCaseRowNum + 1;// 2
		int testCols = 0;
		while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {
			testCols++;
		}
		Object[][] data = new Object[testRows][1];
		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < testCols; cNum++) {
				String colName = excel.getCellData(sheetName, cNum, colStartColNum);
				String testData = excel.getCellData(sheetName, cNum, rNum);
				table.put(colName, testData);
			}
			data[i][0] = table;
			i++;
		}
		return data;
	}
}
