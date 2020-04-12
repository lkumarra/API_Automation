package com.w2a.API_Automation.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListners implements IRetryAnalyzer {

	int count = 0;
	int maxRetry = 3;

	public boolean retry(ITestResult arg0) {
		if (count < maxRetry) {
			count++;
			return true;
		} else {
			return false;
		}
	}

}
