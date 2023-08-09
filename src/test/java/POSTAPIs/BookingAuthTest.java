package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class BookingAuthTest {
	
	@Test
	public void getBookingAuthTokenTest_With_Json_String() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"username\" : \"admin\",\n"
					+ "    \"password\" : \"password123\"\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
					
			
		System.out.println(tokenId);	
		Assert.assertNotNull(tokenId);
		
		
	}
	
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_File() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/basicauth.json"))
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
					
			
		System.out.println(tokenId);	
		Assert.assertNotNull(tokenId);
		
		
	}
	
	
	
	
	

}
