package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestwithParams {
	
	@Test
	public void test1() {
	
		
		RestAssured.baseURI="http://localhost:3000/employees";
		
		RequestSpecification request= RestAssured.given();
		
		Response response=request.param("id", 1).get();
		
		String responsebody=response.getBody().asString();
		
		System.out.println(responsebody);
		
		AssertJUnit.assertTrue(responsebody.contains("Ishani"));
		
		int ResponseCode= response.getStatusCode();
		
		AssertJUnit.assertEquals(ResponseCode, 200);
		
		JsonPath jpath=response.jsonPath();
		List<String> names=jpath.get("name");
		System.out.println(names.get(0));
		AssertJUnit.assertEquals(names.get(0), "Ishani");
		
		String header= response.getHeader("Content-Type");
		System.out.println(header);
		AssertJUnit.assertEquals(header, "application/json; charset=utf-8");
		
		
	}
	
}
