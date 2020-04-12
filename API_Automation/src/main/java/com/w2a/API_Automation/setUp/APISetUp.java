package com.w2a.API_Automation.setUp;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.API_Automation.testUtils.ConfigProperty;
import com.w2a.API_Automation.testUtils.ExcelReader;
import com.w2a.API_Automation.testUtils.Extentmanager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APISetUp {

	public static ConfigProperty configProperty;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\API_TestData.xlsx");
	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuite() {

		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		extentReport = Extentmanager.GetExtent(configProperty.getTestFilePath() + configProperty.getTestReportName());

	}

	@BeforeClass
	public void beforeClass() {

		ExtentTest classLevelTest = extentReport.createTest(getClass().getName());
		classLevelLog.set(classLevelTest);

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		//testLevelLog.get().info("Test Case:- " + method.getName() + " Execution started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
//
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			testLevelLog.get().pass("This Test Case is passed");
//		} else if (result.getStatus() == ITestResult.FAILURE) {
//			testLevelLog.get().fail("This Test Case is Failed");
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			testLevelLog.get().skip("This Test Case is Skipped");
//		}
//		extentReport.flush();

	}

	@AfterSuite
	public void afterSuite() {

	}

	public static RequestSpecification setRequestSpecification() {

		RequestSpecification requestSpecification = given().auth().basic(configProperty.getSecretKey(), "");
		testLevelLog.get().info("Request specification set");
		return requestSpecification;

	}

}
