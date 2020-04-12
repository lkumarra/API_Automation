package com.w2a.API_Automation.testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.API_Automation.POJO.User;
import com.w2a.API_Automation.setUp.APISetUp;
import com.w2a.API_Automation.testUtils.DataProviderClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class TestCreateUserAPI extends APISetUp {
	
	@Test(dataProviderClass= DataProviderClass.class, dataProvider = "dp")
	public void validateCreateUserAPIWithValidData(Hashtable<String, String> data) {
		User user = new User(data.get("name"), data.get("job"));
		Response response = given().contentType(ContentType.JSON).body(user).post("https://reqres.in/api/users");
		response.prettyPrint();
	}

}
