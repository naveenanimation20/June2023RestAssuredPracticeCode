package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAuthWithPojoTest {
	
	@BeforeMethod
    public void setup() {
        RestAssured.filters(new AllureRestAssured());
    }
	
	//POJO - Plain Old Java Object --
	//can not extend any other class
	//oop - encapsulation
	//private class vars -- json body
	//public getter/setter
	
	//serialization --> java object to other format: file, media, json/xml/text/pdf
	//pojo to json -- serialization
	//json to pojo - De-serialization
	//add jckson dependency for the serialization
	
	@Test
	public void getBookingAuthTokenTest_With_Json_String() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Credentials creds = new Credentials("admin", "password123");
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
			
			.body(creds)
			.when().log().all()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
					
			
		System.out.println(tokenId);	
		Assert.assertNotNull(tokenId);
		
		//res json -- username --- compare with getter(getusername())
		
	}
	
	
	

}
