package schemavalidation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SchemaValidationTest {	
		
	@Test
	public void addUserSchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. add user - POST
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/adduser.json"))
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6").
		when()
			.post("/public/v2/users/").
		then().log().all()
			.assertThat()
				.statusCode(201)
				.body(matchesJsonSchemaInClasspath("createuserschema.json"));
	}
	
	
	@Test
	public void getUserSchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. add user - POST
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6").
		when()
			.get("/public/v2/users/").
		then().log().all()
			.assertThat()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("getuserschema.json"));
	}
	
	

}
