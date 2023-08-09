package com.product.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fake.api.Product;
import com.fake.api.ProductLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {
	
	@Test
	public void getProductTest_With_POJO() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
			.when()
				.get("/products");
		
		//json to pojo mapping:de-serialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
			
			for(Product p : product) {
				System.out.println("ID: " + p.getId());
				System.out.println("Title " + p.getTitle());
				System.out.println("Price :" + p.getPrice());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Category: " + p.getCategory());
				System.out.println("Image: " + p.getImage());
				System.out.println("Rate: " + p.getRating().getRate());
				System.out.println("Count: " + p.getRating().getCount());

				System.out.println("--------------");
			}
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void getProductTest_With_POJO_Lombok() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
			.when()
				.get("/products");
		
			
		
		//json to pojo mapping:de-serialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);//JSON to Java Object(POJO)
			
			for(ProductLombok p : product) {
				System.out.println("ID: " + p.getId());
				System.out.println("Title " + p.getTitle());
				System.out.println("Price :" + p.getPrice());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Category: " + p.getCategory());
				System.out.println("Image: " + p.getImage());
				System.out.println("Rate: " + p.getRating().getRate());
				System.out.println("Count: " + p.getRating().getCount());

				System.out.println("--------------");
			}
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	@Test
	public void getProductTest_With_POJO_Lombok_BuilderPattern() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	
						
		
	}
	
					

}
