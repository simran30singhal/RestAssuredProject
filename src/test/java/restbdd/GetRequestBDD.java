package restbdd;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {
	
	@Test
	public void test1() {
		
		RestAssured.given()
				   .baseUri("http://localhost:3000/employees")
				   .when()
				   .get()
				   .then()
				   .log()
				   .body()
				   .statusCode(200);
	}
	
	@Test
	public void test2() {
		
		RestAssured.given()
				   .baseUri("http://localhost:3000/employees")
				   .queryParam("id", "1")
				   .when()
				   .get()
				   .then()
				   .log()
				   .body()
				   .statusCode(200)
				   .body("[0].name", Matchers.equalTo("Ishani")); 
	}
	
	@Test
	public void test3() {
			
		Response response=RestAssured.given()
		   .baseUri("http://localhost:3000/employees")
		   .queryParam("id", "1")
		   .when()
		   .get();
		
		JsonPath jpath=response.jsonPath();
		List<String> names=jpath.get("name");
		System.out.println(names.get(0));
		AssertJUnit.assertEquals(names.get(0), "Ishani");
		
		String header= response.getHeader("Content-Type");
		System.out.println(header);
		AssertJUnit.assertEquals(header, "application/json; charset=utf-8");
	}
}
