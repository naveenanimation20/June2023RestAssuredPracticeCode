package GETAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETAPIWithBDD {
	@BeforeMethod
    public void setup() {
        RestAssured.filters(new AllureRestAssured());
    }
	
	@Test
	public void getProductsTest() {
		
		given().log().all()
			.when()
				.get("https://fakestoreapi.com/products")
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()", equalTo(20))
														.and()
															.body("id", is(notNullValue()))
																.and()
																	.body("title", hasItem("Mens Cotton Jacket"));
										
										
	}
	
	
	@Test
	public void getUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";

		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all()
					.get("/public/v2/users/")
						.then()
							.assertThat()
								.statusCode(200)
								.and()
								.contentType(ContentType.JSON)
									.and()
										.body("$.size()", equalTo(10));
	}
	
	
	
	@Test
	public void getProductDataAPIWithQueryParamTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given()
			.queryParam("limit", 5)
				.when()
					.get("/products")
					.then()
					.assertThat()
					.statusCode(200)
					.and()
					.contentType(ContentType.JSON);

	}
	
	
	@Test
	public void getProductDataAPI_With_Extract_Body() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given().log().all()
									.queryParam("limit", 5)
											.when().log().all()
													.get("/products");
					
		JsonPath js = response.jsonPath();
		
		//get the id of the first product:
		int firstProductId = js.getInt("[0].id");
		System.out.println("firstProductId = " + firstProductId);
		
		String firstProductTitle = js.getString("[0].title");
		System.out.println("firstProductTitle = " + firstProductTitle );
		
		float price = js.getFloat("[0].price");
		System.out.println("price = " + price);
		
		int count = js.getInt("[0].rating.count");
		System.out.println("count = " + count);
		
	}
	
	
	@Test
	public void getProductDataAPI_With_Extract_Body_withJSONArray() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given().log().all()
									.queryParam("limit", 10)
											.when()
													.get("/products");
					
		JsonPath js = response.jsonPath(); //Json Array [{}]
		
		List<Integer> idList = js.getList("id", Integer.class);//0-4==>5
		List<String> titleList = js.getList("title");
		//List<Object> rateList = js.getList("rating.rate");
		List<Float> rateList = js.getList("rating.rate", Float.class);
		List<Integer> countList = js.getList("rating.count");
		
		
		for(int i=0; i<idList.size(); i++) {
			int id = idList.get(i);
			String title = titleList.get(i);
			Object rate = rateList.get(i);
			int count = countList.get(i);
			
			System.out.println("ID: " + id + " " + "Title: " + title + " " + "Rate: " + rate + " " + "Count: "+ count);
		}
		
		
		Assert.assertTrue(rateList.contains(4.9));
		
		
	}
	
	
	@Test
	public void getUserAPI_With_Extract_Body_withJson() {
		
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users/3571519");
		
		JsonPath js = response.jsonPath();
		
		System.out.println(js.getInt("id"));
		System.out.println(js.getString("email"));

		
	}
	
	@Test
	public void getUserAPI_With_Extract_Body_withJson_Extract() {
		
		RestAssured.baseURI = "https://gorest.co.in";

//		int userId = given().log().all()
//			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//				.when().log().all()
//					.get("/public/v2/users/3571519")
//						.then()
//							.extract().path("id");
//								
//		
//		System.out.println(userId);
		
		
		Response response = given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when()
						.get("/public/v2/users/3571519")
							.then()
								.extract()
									.response();
							
		int userId = response.path("id");	
		String email = response.path("email");				

			
			System.out.println(userId);
			System.out.println(email);
	}
	
	
	
	
	@Test
	public void fackStoreTest() {
		Response response = given().log().all()
		.when().log().all()
		.get("https://fakestoreapi.com/products");
		JsonPath js = response.jsonPath();
		List<Float> priceList = js.getList("price", Float.class);
		System.out.println(priceList);
		Assert.assertTrue(priceList.contains(12.99f));

		
	}

}
	
	
	
	
	
	


