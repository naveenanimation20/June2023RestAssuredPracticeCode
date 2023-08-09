package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;




public class OAuth2Test {
	
	static String accessToken;	
	@BeforeMethod
	public void getAccessToken() {
		//1. POST - get the access token
				RestAssured.baseURI = "https://test.api.amadeus.com";
				
				 accessToken = given()
					//.header("Content-Type", "application/x-www-form-urlencoded")
					.contentType(ContentType.URLENC)
					.formParam("grant_type", "client_credentials")
					.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
					.formParam("client_secret", "VjjgfcJilNAzcSJw")
				.when()
					.post("/v1/security/oauth2/token")
				.then()
					.assertThat()
						.statusCode(200)
						.extract().path("access_token");
					
				System.out.println(accessToken);
				
	}
	
	
	@Test
	public void getFlightInfoTest() {

		//2. get flight info: GET
	Response flightDataResponse = 	given().log().all()
		.header("Authorization", "Bearer "+accessToken)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", 200)
			.when().log().all()
				.get("/v1/shopping/flight-destinations")
			.then().log().all()
				.assertThat()
					.statusCode(200)
						.and()
							.extract()
								.response();
	JsonPath js = flightDataResponse.jsonPath();
	String type = js.get("data[0].type");
	System.out.println(type);//flight-destination
	
	
	
		
	}
	
	
	
	
	

}
