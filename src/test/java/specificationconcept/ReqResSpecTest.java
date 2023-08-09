package specificationconcept;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecTest {
	
public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.build();
		
		return requestSpec;
	}


public static ResponseSpecification get_res_spec_200_OK_With_Body() {
	ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.expectBody("$.size()", equalTo(10))
		.expectBody("id", hasSize(10))
		.build();
	
	return res_spec_200_ok;
}


@Test
public void getUser_With_Req_Res_Spec_Test() {
	
	given()
		.spec(user_req_spec())
			.get("/public/v2/users")
				.then()
					.assertThat()
						.spec(get_res_spec_200_OK_With_Body());
	
	
	
	
	
}
	
	


}
