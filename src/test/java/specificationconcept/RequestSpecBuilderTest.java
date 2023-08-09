package specificationconcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {

	

	
	
	
	public static RequestSpecification user_req_spec() {

		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.build();

		return requestSpec;
	}

	@Test
	public void getUser_With_Request_Spec() {
		given().log().all().spec(user_req_spec()).get("/public/v2/users").then().statusCode(200);
	}

	@Test
	public void getUser_With_Param_Request_Spec() {
		given().log().all().queryParam("name", "naveen").queryParam("status", "active").spec(user_req_spec())
				.get("/public/v2/users").then().statusCode(200);
	}
	
	
	
	
	
	@Test(priority = 1)
	public void testPostOrder() {
	    String accessToken = "0f9e9fb043905072793dcf86dbe985b0cf2798dde153cd2b51004676d0284f80";
	    String jsonString = "{\"bookId\" : \"1\",\"customerName\" : \"Nada\"}";

	    RequestSpecification request = RestAssured.given();
	    request.header("Content-Type", "application/json")
	           .header("Authorization", "Bearer " + accessToken);
	    request.contentType(ContentType.JSON);
	    request.baseUri("https://simple-books-api.glitch.me/orders");
	    request.body(jsonString);

	    Response response = request.post();
	    System.out.println(response.asString());

	    response.then().statusCode(201);

	    System.out.println(response.getStatusCode());
	    System.out.println(response.getTime());
	}

}
