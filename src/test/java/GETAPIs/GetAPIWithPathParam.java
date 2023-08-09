package GETAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAPIWithPathParam {
	
	//query vs path
	//<k,v>    <anykey, value>
	//?         /
	
	@Test
	public void getCircuitDataAPIWith_YearTest() {
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
			.pathParam("year", "2017")
				.when().log().all()
					.get("/api/f1/{year}/circuits.json")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.body("MRData.CircuitTable.season", equalTo("2017"))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

	}
	
	
	//2017 -- 20
	//2016 -- 21
	//1966 -- 9 
	
	
	@DataProvider
	public Object[][] getCircuitYearData() {
		return new Object[][] {
			
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
		};
	}
	
	
	@Test(dataProvider = "getCircuitYearData")
	public void getCircuitDataAPIWith_Year_DataProvider(String seasonYear, int totalCircuits) {
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
			.pathParam("year", seasonYear)
				.when().log().all()
					.get("/api/f1/{year}/circuits.json")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.body("MRData.CircuitTable.season", equalTo(seasonYear))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));

	}
	
	
	
	

}
