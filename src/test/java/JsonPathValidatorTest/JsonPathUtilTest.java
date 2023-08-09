package JsonPathValidatorTest;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathUtilTest {
	
	
	 @Test
	    public void getUserValue() {
		 //single value:
	        String baseUrl = "https://gorest.co.in";
	        String url = baseUrl + "/public/v2/users/4220436";

	        // Send the GET request and extract the response
	        Response response = given().log().all()
	        			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	        				.get(url);

	        
	        JsonPathValidator js = new JsonPathValidator();
	        int userId =  js.read(response, "$.id");
	        System.out.println("userId: " + userId);

	       
	    }
	
	
	
	
	 @Test
	    public void getCountryValue() {
	        // Specify the base URL and path parameter (year) for the GET call
	        String baseUrl = "http://ergast.com/api/f1";
	        String year = "2017";
	        String url = baseUrl + "/" + year + "/circuits.json";

	        // Send the GET request and extract the response
	        Response response = given().get(url);

	        
	        JsonPathValidator js = new JsonPathValidator();
	        List<String> countryList =  js.readList(response, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
	        System.out.println("Country: " + countryList);

	       
	    }

	 
	 
	 
	 @Test
		public void getCircuitDataAPIWith_YearTest() {
			RestAssured.baseURI = "http://ergast.com";

			Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.json");
			
			JsonPathValidator js = new JsonPathValidator();
			
			//
			int totalCircuits = js.read(response, "$.MRData.CircuitTable.Circuits.length()");
			System.out.println(totalCircuits);
			
			
			//
	        List<String> countryList =  js.readList(response, "$..Circuits..country");
	        System.out.println("Country: " + countryList);

		}
	 
	 @Test
		public void getProductTest() {

			RestAssured.baseURI = "https://fakestoreapi.com";

			Response response = given().when().get("/products");
			
			JsonPathValidator js = new JsonPathValidator();
			List<Float> rateLessThanThree = js.readList(response, "$[?(@.rating.rate < 3)].rating.rate");
			System.out.println(rateLessThanThree);
			
			
			
			System.out.println("---------------");
			// with two attributes
			List<Map<String, Object>> jewellryList = js.readListOfMaps(response, "$[?(@.category == 'jewelery')].[\"title\",\"price\"]");

//			List<Map<String, Object>> jewellryList = JsonPath.read(jsonResponse,
//					"$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
			System.out.println(jewellryList);
	
			for (Map<String, Object> product : jewellryList) {
				String title = (String) product.get("title");
				Object price = (Object) product.get("price");
	
				System.out.println("title : " + title);
				System.out.println("price : " + price);
				System.out.println("--------");
	
			}
			
			System.out.println("---------------");
			// with three attributes
			List<Map<String, Object>> jewellryList1 = js.readListOfMaps(response, "$[?(@.category == 'jewelery')].[\"title\",\"price\", \"image\"]");

			System.out.println(jewellryList1);
	
			for (Map<String, Object> product : jewellryList1) {
				String title = (String) product.get("title");
				Object price = (Object) product.get("price");
				String image = (String) product.get("image");

	
				System.out.println("title : " + title);
				System.out.println("price : " + price);
				System.out.println("image : " + image);
				System.out.println("--------");
	
			}

			
			
			

	 }
	 
}
