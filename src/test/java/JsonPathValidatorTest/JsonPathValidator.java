package JsonPathValidatorTest;


import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {

    private String getJsonResponseAsString(Response response) {
        return response.getBody().asString();
    }

    public <T> T read(Response response, String jsonPath) {
        String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            throw new AssertionError("JSON path not found: " + jsonPath, e);
        }
    }

    public <T> List<T> readList(Response response, String jsonPath) {
        String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            throw new AssertionError("JSON path not found: " + jsonPath, e);
        }
    }

    public <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
        String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            throw new AssertionError("JSON path not found: " + jsonPath, e);
        }
    }
}
