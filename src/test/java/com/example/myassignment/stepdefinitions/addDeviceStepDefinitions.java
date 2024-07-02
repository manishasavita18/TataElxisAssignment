package com.example.myassignment.stepdefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class addDeviceStepDefinitions {

    private String baseURI = "https://api.restful-api.dev/objects";
    private Response response;

    @Given("the base URI is {string}")
    public void setBaseURI(String uri) {
        baseURI = uri;
    }

    @When("I send a POST request to {string} with the following payload:")
    public void i_send_a_POST_request_to_with_payload(String endpoint, String payload) {

        System.out.println("Sending PUT request to: " + endpoint);
        System.out.println("Request payload: " + payload);
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(endpoint);
        System.out.println("Response body: " + response.prettyPrint()); // reponse body
    }

    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the response body should contain:")
    public void verifyResponseBody(Map<String, String> expectedValues) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue();

            // Split the key by dots to handle nested JSON objects
            String[] keys = key.split("\\.");
            JSONObject nestedObject = responseBody;

            // Traverse through the nested keys to get to the target value
            for (int i = 0; i < keys.length - 1; i++) {
                if (nestedObject.has(keys[i])) {
                    nestedObject = nestedObject.getJSONObject(keys[i]);
                } else {
                    throw new AssertionError("Key '" + keys[i] + "' not found in the response body");
                }
            }

            String targetKey = keys[keys.length - 1];
            if (nestedObject.has(targetKey)) {
                String actualValue = nestedObject.get(targetKey).toString();
                assertThat("Value for key '" + key + "' does not match", actualValue, is(expectedValue));
            } else {
                throw new AssertionError("Key '" + targetKey + "' not found in the response body");
            }
        }
    }

    @Then("the \"id\" and \"createdAt\" fields should not be null")
    public void validateIdAndCreatedAtFields() {
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertThat(responseBody.getString("id"), is(notNullValue()));
        assertThat(responseBody.getString("createdAt"), is(notNullValue()));
    }

    private String loadJSONFromFile(String filePath) throws IOException {
        return IOUtils.toString(
                getClass().getClassLoader().getResourceAsStream(filePath),
                StandardCharsets.UTF_8
        );
    }





}
