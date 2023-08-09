package DeleteAPIs;

import org.testng.annotations.Test;

import PUTAPIs.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;


public class DeleteUserTest {
	//1. post - create user - userID -- 201
	//2. delete - delete user -- /userID -- 204
	//3. get --  get user -- /userID -- 404
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void deleteUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. POST - Create User
		User user = new User("Naveen", getRandomEmailId(), "male", "active");
		Response response = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.body(user)
					.when().log().all()
						.post("/public/v2/users");
		
		response.prettyPrint();
		
		Integer userId = response.jsonPath().get("id");
		System.out.println("user id : " + userId);
		
		System.out.println("-------------------------");
		
		//2. Delete - deleter user
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
			.delete("/public/v2/users/"+userId)
				.then().log().all()
					.assertThat()
						.statusCode(204);
		
		//3. Get the user -- GET:
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
			.get("/public/v2/users/"+userId)
				.then().log().all()
					.assertThat()
						.statusCode(404)
							.and()
								.assertThat()
									.body("message", equalTo("Resource not found"));
		
		
		
		
	}
	
	
	

}
