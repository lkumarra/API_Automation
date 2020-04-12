package com.w2a.API_Automation.API;

import java.util.Hashtable;

import com.w2a.API_Automation.setUp.APISetUp;
import com.w2a.API_Automation.testUtils.TestUtil;

import io.restassured.response.Response;

public class CustomerAPI extends APISetUp{

	public static Response sendPostRequestWithValidData(Hashtable<String, String> data) {
		
		Response response = TestUtil.setFormParam(data.get("arguments"), setRequestSpecification()).post(data.get("endpoint"));
		return response;

	}

//	public static Response sendPostRequestWithInValidData() {
//
//	}

}
