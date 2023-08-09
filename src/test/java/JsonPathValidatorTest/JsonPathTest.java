package JsonPathValidatorTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {
	
	
	 @Test
	    public void getCountryValue() {
	        // Specify the base URL and path parameter (year) for the GET call
	        String baseUrl = "http://ergast.com/api/f1";
	        String year = "2017";
	        String url = baseUrl + "/" + year + "/circuits.json";

	        // Send the GET request and extract the response
	        Response response = given().get(url);
	        	        

	        // Extract the response body as a string
	        String jsonResponse = response.getBody().asString();

	        // Use JsonPath to extract the country value using the given JSONPath expression
	        List<String> countryList = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");

	        // Print the country value
	        System.out.println("Country: " + countryList);
	       
	    }
	
	
	

	@Test
	public void getCircuitDataAPIWith_YearTest() {
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.json");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println(totalCircuits);

		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");
		System.out.println(countryList.size());
		System.out.println(countryList);

	}

	@Test
	public void getProductTest() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

//		List<Float> rateLessThanThree = JsonPath.read(jsonResponse, "$[?(@.rating.rate < 3)].rating.rate");
//		System.out.println(rateLessThanThree);

		System.out.println("---------------");

		// with two attributes
//		List<Map<String, Object>> jewellryList = JsonPath.read(jsonResponse,
//				"$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
//		System.out.println(jewellryList);
//
//		for (Map<String, Object> product : jewellryList) {
//			String title = (String) product.get("title");
//			Object price = (Object) product.get("price");
//
//			System.out.println("title : " + title);
//			System.out.println("price : " + price);
//			System.out.println("--------");
//
//		}
//		
		System.out.println("---------------");


		// with three attributes
//		List<Map<String, Object>> jewellryList2 = JsonPath.read(jsonResponse,
//				"$[?(@.category == 'jewelery')].[\"id\",\"image\"]");
//		
//		System.out.println(jewellryList2);
//
//		for (Map<String, Object> product : jewellryList2) {
//			String image = (String) product.get("image");
//			Integer id = (Integer) product.get("id");
//
//
//			System.out.println("image : " + image);
//			System.out.println("id : " + id);
//			System.out.println("--------");
//
//		}
		
		
//		List<Integer> countList =JsonPath.read(jsonResponse,"$[?(@.category =='jewelery')].rating.count");
//		System.out.println(countList);
		
		//List<Map<String, Object>> jewelryList = JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[title, price, rating.count]");
		//List<Map<String, Object>> jewelryList = JsonPath.parse(jsonResponse).read("$[?(@.category == 'jewelery')].{title: @.title, price: @.price, count: @.rating.count}");

		//List<Map<String, Object>> jewelryList = JsonPath.parse(jsonResponse).read("$[?(@.category == 'jewelery')]['title', 'price', 'rating.count']");
		//List<Map<String, Object>> jewelryList = JsonPath.parse(jsonResponse).read("$[?(@.category == 'jewelery')].{title: @.title, price: @.price, count: @.rating.count}");
		List<Map<String, Object>> jewelryList = JsonPath.parse(jsonResponse).read("$[?(@.category == 'jewelery')].[\"title\", \"price\"][\"'@.rating.count'\"]");



		System.out.println(jewelryList);
		
		
		for (Map<String, Object> jewelry : jewelryList) {
		    String title = (String) jewelry.get("title");
		    Object price = (Object) jewelry.get("price");
		    Object count = (Object) jewelry.get("count");

		    System.out.println("Title: " + title);
		    System.out.println("Price: " + price);
		    System.out.println("Count: " + count);
		    System.out.println();
		}
		
//		for (Map<String, Object> product : fourAttr) {
//		String image = (String) product.get("title");
//		Object price = (Object) product.get("price");
//		Integer id = (Integer) product.get("id");
//		Integer count = (Integer) product.get("count");
//
//
//
//		System.out.println("image : " + image);
//		System.out.println("price : " + price);
//		System.out.println("id : " + id);
//		System.out.println("count : " + count);
//
//		System.out.println("--------");

	}

		

	

}
