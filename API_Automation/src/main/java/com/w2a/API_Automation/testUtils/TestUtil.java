package com.w2a.API_Automation.testUtils;

import io.restassured.specification.RequestSpecification;

public class TestUtil {

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

}
