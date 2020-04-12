package com.w2a.API_Automation.testUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.w2a.API_Automation.setUp.APISetUp;

import io.restassured.specification.RequestSpecification;

public class TestUtil extends APISetUp {

	public static RequestSpecification setFormParam(String arguments, RequestSpecification reqSpec) {
		String[] listOfArguments = arguments.split(",");
		for (String singleArgument : listOfArguments) {
			String[] keyValue = singleArgument.split(":");
			String key = keyValue[0];
			String value = keyValue[1];
			reqSpec.formParam(key, value);
		}
		return reqSpec;
	}
	
	public static void archiveTestReport() {
		
		String reportName = configProperty.getTestReportName();

		String lastTestReportFilePath = System.getProperty("user.dir")+"/src/test/resources/TestReports/";
		String archiveReportPath = System.getProperty("user.dir")+"/src/test/resources/ArchivedTestReports/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String formatedDate = dateFormate.format(date);
		String archiveTestReportName = formatedDate + "_" + reportName;

		File oldReport = new File(lastTestReportFilePath + reportName);

		File newFile = new File(archiveReportPath + archiveTestReportName);
		
		System.out.println(oldReport.exists());
		
		if (oldReport.exists()) {
			System.out.println("inside if");
			oldReport.renameTo(newFile);
			oldReport.delete();
		}

}

}
