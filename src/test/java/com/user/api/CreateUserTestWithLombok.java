package com.user.api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class CreateUserTestWithLombok {
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com";
	}
	
	
//	@Test
//	public void createUserTest() {
//		RestAssured.baseURI = "https://gorest.co.in";
//		
//		User user = new User("Naveen", getRandomEmailId(), "male", "active");
//		
//		Response response = RestAssured.given()
//					.contentType(ContentType.JSON)
//					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//					.body(user)
//					.when()
//						.post("/public/v2/users");
//		
//		Integer userId = response.jsonPath().get("id");
//		System.out.println("user id : " + userId);
//		
//		//GET API to get the same user:
//		
//		//2. get the same user and verify it: GET
//		Response getResponse =	given()
//				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//					.when().log().all()
//						.get("/public/v2/users/"+ userId);
//		
//		//de-serialization:
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);
//			
//			System.out.println(userRes.getId() + ":" + userRes.getEmail() + ": " + userRes.getStatus() + ": " + userRes.getGender());
//			Assert.assertEquals(userId, userRes.getId());
//			Assert.assertEquals(user.getName(), userRes.getName());
//			Assert.assertEquals(user.getEmail(), userRes.getEmail());
//
//			
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//							
//			
//		
//	}
	
	
	
	@Test
	public void createUser_WithBuilderPattern_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		User user = new User.UserBuilder()
				.name("Naveen")
				.email(getRandomEmailId())
				.status("active")
				.gender("male")
				.build();
		
		
		
		Response response = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.body(user)
					.when().log().all()
						.post("/public/v2/users");
		
		Integer userId = response.jsonPath().get("id");
		System.out.println("user id : " + userId);
		
		//GET API to get the same user:
		
		//2. get the same user and verify it: GET
		Response getResponse =	given()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when().log().all()
						.get("/public/v2/users/"+ userId);
		
		//de-serialization:
		ObjectMapper mapper = new ObjectMapper();
		try {
			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);
			
			System.out.println(userRes.getId() + ":" + userRes.getEmail() + ": " + userRes.getStatus() + ": " + userRes.getGender());
			Assert.assertEquals(userId, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());

			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
							
			
		
	}
	
	
	
	

}
