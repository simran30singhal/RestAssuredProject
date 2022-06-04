package restbdd;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRequestBDD {
	
	@Test
	public void test2() {
		
		RestAssured.given()
				   .baseUri("http://localhost:3000/employees")
				   .when()
				   .delete("/11")
				   .then()
				   .log()
				   .body()
				   .statusCode(200);
				   
	}
}
