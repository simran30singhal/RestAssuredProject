package apichaining;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	Response response;
	String BaseURI = "http://localhost:3000/employees";

	@Test
	public void test1() {

		response = GetMethodwithAllEmployees();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		response = PostMethod("Rohit", "8000");
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int emp_id = jpath.get("id");
		System.out.println("id=" + emp_id);

		response = PutMethod(emp_id, "Raj", "4000");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		AssertJUnit.assertEquals(jpath.get("name"), "Raj");
		
		response=DeleteMethod(emp_id);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);	
		AssertJUnit.assertEquals(response.getBody().asString(), "{}");
		
		response=GetDeletedEmp(emp_id);
		AssertJUnit.assertEquals(response.getStatusCode(), 404);	
		AssertJUnit.assertEquals(response.getBody().asString(), "{}");

	}

	public Response GetMethodwithAllEmployees() {

		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		return response;
	}

	public Response PostMethod(String name, String salary) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();

		jobj.put("name", name);
		jobj.put("salary", salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(jobj.toString())
									.post("/create");
		return response;
	}

	public Response PutMethod(int emp_id, String name, String salary) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();

		jobj.put("name", name);
		jobj.put("salary", salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(jobj.toString())
									.put("/" + emp_id);
		return response;

	}

	public Response DeleteMethod(int emp_id) {
		

		RestAssured.baseURI=BaseURI;
		
		RequestSpecification request= RestAssured.given();
		
		Response response=request.delete("/"+emp_id);
		return response;

	}
	public Response GetDeletedEmp(int emp_id) {
	
		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();
		Response response = request.get("/"+emp_id);
		return response;
	}
	}

