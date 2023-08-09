package com.fake.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GetProduct {
	
	@Test
	public void getProductTest_BDD_Style_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given()
			.when()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.contentType(ContentType.JSON)
								.and()
									.body("$.size()", equalTo(20))
										.and()
											.body("id", is(notNullValue()))
												
												.and()
													.body("title", hasItem("Rain Jacket Women Windbreaker Striped Climbing Raincoats"));	
	}
	
	
	
	@Test
	public void getUserTest_BDD_Style_WithHeader_2() {
		
		RestAssured.baseURI = "https://gorest.co.in";	
				given()
				 .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when()
						.get("/public/v2/users")
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.contentType(ContentType.JSON)
												.and()
													.body("$.size()", equalTo(10));
	}
	
	
	@Test
	public void getProductTest_BDD_Style_With_Limit_3() {
		
	Response response = given()
								.queryParam("limit", 5)
									.when()
										.get("https://fakestoreapi.com/products");
					
		
	System.out.println(response.statusCode() + " " + response.statusLine());
	response.prettyPrint();
		
	}
	
	
	@Test
	public void getProductTest_BDD_Style_With_JSON_ARRAY_Limit_4() {
		
	Response response = given()
								.queryParam("limit", 5)
									.when()
										.get("https://fakestoreapi.com/products")
											.then().log().all()
												.extract()
													.response();
	
	JsonPath js = response.jsonPath();

    // Get the ID of the first product
	 int firstProductId = js.getInt("[0].id");
     System.out.println("First Product ID: " + firstProductId);
     
     // Get the rate of the second product
     double secondProductRate = js.getDouble("[1].rating.rate");
     System.out.println("Second Product Rate: " + secondProductRate);

     // Get the total size of the array
     int arraySize = js.getList("$").size();
     System.out.println("Array Size: " + arraySize);
					
		
	}
	
	
	@Test
	public void getUserTest_BDD_Style_WithHeader_WithJsonObject_6() {
		
		RestAssured.baseURI = "https://gorest.co.in";	
		Response response = 
				given()
				 .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when()
						.get("/public/v2/users/3477375");
		
		JsonPath js = response.jsonPath();
		System.out.println(js.getInt("id"));
		System.out.println(js.getString("email"));

	}
		
	
	
	
	@Test
	public void getProductTest_Partial_BDD_5() {
		
	Response response = given()
							.when()
								.get("https://fakestoreapi.com/products");
					
		
	System.out.println(response.statusCode() + " " + response.statusLine());
	response.prettyPrint();
	JsonPath js = response.jsonPath();
	
	
	List<Integer> ids = js.getList("id");
	List<Float> rates = js.getList("rating.rate", Float.class);
	List<Integer> countList = js.getList("rating.count");
	List<String> titleList = js.getList("title");

	for (int i = 0; i < ids.size(); i++) {
	    int id = ids.get(i);
	    float rate = rates.get(i);
	    String title = titleList.get(i);
	    int count = countList.get(i);
	    
	    System.out.println("ID: " + id + ", Rate: " + rate + ", title: " + title + " , count: " + count);
	}
	
	
	}
	
	
	

}
