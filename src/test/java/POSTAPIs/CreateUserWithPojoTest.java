package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class CreateUserWithPojoTest {
	
	//1. direct supply the json string
	//2. pass th json file
	//3. pojo - java objects -- to json with the help of jackson/rest assured
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com";
	}
	
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Naveen", getRandomEmailId(), "male", "active");

		//1. add user - POST
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.body(user)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6").
		when()
			.post("/public/v2/users/").
		then().log().all()
			.assertThat()
				.statusCode(201)
				.and()
				.body("name", equalTo(user.getName()))
				.extract()
					.path("id");
				
		System.out.println("user id -->" + userId);
		
		//2. get the same user and verify it: GET
		given()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.when().log().all()
				.get("/public/v2/users/"+ userId)
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.body("id", equalTo(userId))
										.and()
											.body("name", equalTo(user.getName()))
												.and()
												.body("status", equalTo(user.getStatus()))
													.and()	
													.body("email", equalTo(user.getEmail()));	
		
		
	}
	
	
	

}
