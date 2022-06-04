package restApiXML;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {
	
	@Test
	public void test1() {
		
		RestAssured.given()
		   .baseUri("https://chercher.tech/sample/api/books.xml")
		   .when()
		   .get()
		   .then()
		   .log()
		   .body()
		   .statusCode(200);
	}
	
	@Test
	public void test2() {
		
		Response response=RestAssured.given()
		   .baseUri("https://chercher.tech/sample/api/books.xml")
		   .when()
		   .get();
		
		NodeChildrenImpl books=response.then().extract().path("bookstore.book.title");
		
		System.out.println("The Books names are: "+books.toString());
		System.out.println("First Book is: "+books.get(0).toString());
		System.out.println("Second Book is: "+books.get(1).toString());
		System.out.println("Language of First Book is: "+books.get(0).getAttribute("lang").toString());
		
		for(String i:books) {
			System.out.println(i);
		}
		
		
		NodeChildrenImpl prices=response.then().extract().path("bookstore.book.price");
		System.out.println("First Book paperback price is: "+prices.get(0).children().get("paperback").toString());
		System.out.println("First Book hardcover price is: "+prices.get(0).children().get("hardcover").toString());
		System.out.println("Second Book price is: "+prices.get(1).toString());
	}
	
	
}
