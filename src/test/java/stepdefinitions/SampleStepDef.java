package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojo.SamplePojo;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SampleStepDef extends BaseClass {
    Map<String, String> queryParam;

    @Given("I get the response from rick and morty api")
    public void getApiResp(Map<String, String> dataMap) {
        queryParam = new HashMap<>();
        //iterate the dataMap which contains data of query param
        dataMap.forEach((k, v) -> {
            //check if the value fo any key is not null
            // if it is not null then add that key and value in the queryParam object
            if (Objects.nonNull(v)) {
                queryParam.put(k, v);
            }
        });
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://rickandmortyapi.com")
                .basePath("/api/character")
                .header("Accept", "application/json")
                .queryParams(queryParam)
                .log().all()
                .when().get();
    }

    @Then("I verify the response with status code {int}")
    public void iVerifyTheResponseWithStatusCode(int expectedStatusCode) {

        Assert.assertEquals(expectedStatusCode, response.getStatusCode());

        int totalPages = response.jsonPath().getInt("info.pages");

        for (int i = 1; i <= totalPages; i++) {
            queryParam.put("page", String.valueOf(i));
            response = given()
                    .baseUri("https://rickandmortyapi.com")
                    .basePath("/api/character")
                    .header("Accept", "application/json")
                    .queryParams(queryParam)
                    .log().all()
                    .when().get();
            response.prettyPrint();

            //deserialize the response into SamplePojo class
            SamplePojo samplePojo = response.as(SamplePojo.class);
            // get the values from Result field
            List<Map<String, Object>> results = samplePojo.getResults();

            //iterate result list and check query params value in each object (Map<String, Object>)
            results.forEach(map -> {
                // get each key from queryParam object and verify in the map
                Set<String> keys = queryParam.keySet();
                //iterate the keys and get each key then verify that key value in map
                queryParam.remove("page");
                keys.forEach(key -> {
                    if(key.equals("name")){
                        Assert.assertTrue(
                                map.get(key).toString().toLowerCase().contains(queryParam.get(key).toLowerCase()));
                    }else{
                        Assert.assertEquals(queryParam.get(key), map.get(key));
                    }
                });
            });

            for (Map<String, Object> obj : results) {
                Set<String> keys = queryParam.keySet();
                queryParam.remove("page");
                for (String key : keys) {
                    String actualValue = obj.get(key).toString();
                    String expectedValue = queryParam.get(key);
                    if(key.equals("name")){
                        Assert.assertTrue(
                                actualValue.toLowerCase().contains(expectedValue.toLowerCase()));
                    }else{
                        Assert.assertEquals(expectedValue, actualValue);
                    }

                }
            }


        }


    }

    @Then("I verify name in the response")
    public void iVerifyNameInTheResponse() {

        int totalPage = response.jsonPath().getInt("info.pages");
        //get the result of each page
        for (int i = 1; i <= totalPage; i++) {
            queryParam.put("page", String.valueOf(i));
            response = given()
                    .baseUri("https://rickandmortyapi.com")
                    .basePath("/api/character")
                    .header("Accept", "application/json")
                    .queryParams(queryParam)
                    .log().all()
                    .when().get();
            response.prettyPrint();
            //deserialize the response into SamplePojo class
            SamplePojo samplePojo = response.as(SamplePojo.class);
            // get the values from Result field
            List<Map<String, Object>> results = samplePojo.getResults();
            for (Map<String, Object> obj : results) {
                Set<String> keys = queryParam.keySet();
                queryParam.remove("page");
                for (String key : keys) {
                    String actualValue = obj.get(key).toString();
                    String expectedValue = queryParam.get(key);

                }
            }
        }
    }
}
