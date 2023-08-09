package com.fake.api;


import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class MultiPartFileTest {

    @Test
    public void postFormDataWithFile() {
        String name = "naveen";
        String filePath = "/Users/naveenautomationlabs/Desktop/Auth_PDF.pdf";

        RestAssured.given().log().all()
                .multiPart("name", name)
                .multiPart("bank", new File(filePath))
                .when()
                .post("http://httpbin.org/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
