package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestWithoutBDD {
	RequestSpecification request;

	
	//NON BDD Approach:
	
	@BeforeTest
	public void setup() {
		// Request:
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");

	}

	@Test
	public void getAllUsersAPITest() {

		Response response = request.get("/public/v2/users/");

		// =================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);

		// verification point:
		Assert.assertEquals(statusCode, 200);

		// status mesg:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

		// fetch header:
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		System.out.println("-----------------");
		// fetch all headers:
		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}

	@Test
	public void getAllUsersWithQueryParameterAPITest() {

		request.queryParam("name", "naveen");
		request.queryParam("status", "active");

		Response response = request.get("/public/v2/users");

		// =================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);

		// verification point:
		Assert.assertEquals(statusCode, 200);

		// status mesg:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

	}

	@Test
	public void getAllUsersWithQueryParameter_WithHashMap_APITest() {

		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("gender", "male");

		request.queryParams(queryParamsMap);

		Response response = request.get("/public/v2/users");

		// =================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);

		// verification point:
		Assert.assertEquals(statusCode, 200);

		// status mesg:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

	}
	
	

}
