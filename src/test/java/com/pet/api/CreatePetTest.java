package com.pet.api;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {
	
	
	@Test
	public void createPetTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		Category category = new Category(1, "Dog");
		List<String> photoUrls =  Arrays.asList("https://dog.com", "https://dog1.com");
		
		Tag tag1 = new Tag(5, "red");
		Tag tag2 = new Tag(6, "black");

		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		PetLombok pet = new PetLombok(300, category, "Ronney", photoUrls, tags, "available");
		
		Response response = RestAssured.given()
					.contentType(ContentType.JSON)
					.body(pet)
						.when()
							.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
					
		//De-serialize:
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petRes = mapper.readValue(response.getBody().asString(), PetLombok.class);
			
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());

			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());

			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Test
	public void createPet_WithBuilderPattern_Test() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		Category category =	new Category.CategoryBuilder()
						.id(400)
						.name("Animal")
						.build();
		
		Tag tag1 = new Tag.TagBuilder()
					.id(50)
					.name("red")
					.build();
		Tag tag2 = new Tag.TagBuilder()
				.id(51)
				.name("black")
				.build();
		
		
		PetLombok pet =	new PetLombok.PetLombokBuilder()
					.id(5000)
					.category(category)
					.name("Robby")
					.photoUrls(Arrays.asList("https://cat.com", "https://cat1.com"))
					.tags(Arrays.asList(tag1, tag2))
					.status("available")
					.build();
		
		Response response = RestAssured.given()
					.contentType(ContentType.JSON)
					.body(pet)
						.when()
							.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
					
		//De-serialize:
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petRes = mapper.readValue(response.getBody().asString(), PetLombok.class);
			
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());

			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());

			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	

}
