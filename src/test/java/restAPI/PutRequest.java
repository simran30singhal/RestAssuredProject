package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {
	
	@Test
	public void test1() {
	
		
		RestAssured.baseURI="http://localhost:3000/employees";
		
		
		Map<String,Object> Mapobj= new HashMap<String,Object>();
		
		Mapobj.put("name", "Alex");
		Mapobj.put("salary", "7000");
		
		RequestSpecification request= RestAssured.given();
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(Mapobj)
								 .put("/5");
		
		String responsebody=response.getBody().asString();
		
		System.out.println(responsebody);
		int ResponseCode= response.getStatusCode();
		
		AssertJUnit.assertEquals(ResponseCode, 200);
		JsonPath jpath=response.jsonPath();
		jpath.get("id");
		System.out.println("id="+jpath.get("id"));
		
		
	}
}
