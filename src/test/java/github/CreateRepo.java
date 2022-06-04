package github;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateRepo {

	@Test
	public void test() {

		RestAssured.baseURI = "https://api.github.com/users/simran30singhal/repos";

		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		String body = response.getBody().asString();
		System.out.println(body);
		int ResponseCode = response.getStatusCode();
		AssertJUnit.assertEquals(ResponseCode, 200);
	}

	@Test
	public void test2() throws IOException {

		RestAssured.baseURI = "https://api.github.com/user/repos";
		RequestSpecification request = RestAssured.given();

		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));

		Response response = request.contentType(ContentType.JSON)
								   .accept(ContentType.JSON)
								   .auth()
								   .oauth2("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")
								   .body(dataBytes)
								   .post();

		String responsebody = response.getBody().asString();

		System.out.println(responsebody);
		int ResponseCode = response.getStatusCode();

		AssertJUnit.assertEquals(ResponseCode, 201);

	}

}
