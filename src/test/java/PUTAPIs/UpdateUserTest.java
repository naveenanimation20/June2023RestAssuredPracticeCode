package PUTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class UpdateUserTest {
	//create user - POST --> userId
	//update user - PUT --> /userId
	
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Naveen", getRandomEmailId(), "male", "active");
		
		//1. POST - Create User
		Response response = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.body(user)
					.when().log().all()
						.post("/public/v2/users");
		
		Integer userId = response.jsonPath().get("id");
		System.out.println("user id : " + userId);
		
		System.out.println("-------------------------");
		
		//update the exisitng user:
		user.setName("Naveen Automation Labs");
		user.setStatus("inactive");
		
		//2. PUT - Update User
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.body(user)
		.when()
			.put("/public/v2/users/"+userId)//patch will also work
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.assertThat()
									.body("id", equalTo(userId))
										.and()
											.body("name", equalTo(user.getName()))
												.and()
													.body("status", equalTo(user.getStatus()));
		
		
		
	}
	
	
	
	

}
