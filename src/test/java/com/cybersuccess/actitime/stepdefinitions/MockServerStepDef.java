package com.cybersuccess.actitime.stepdefinitions;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static io.restassured.RestAssured.given;

public class MockServerStepDef extends BaseClass {
    static WireMockServer wireMockServer;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("This is before all hook.....in MockServerStepDef.. ");

       /*
        * create instance of wiremockserver class
        * define on which port this mock server should run
        * This instance will create baseUri as http://localhost:808
        */
        wireMockServer= new WireMockServer();

        //configure wiremock server on 8082 port
//        WireMock.configureFor(8082);

        //start wiremock server
        wireMockServer.start();




        String responseBody = "{\n" +
                "  \n" +
                "  \"countries\": [\n" +
                "\t{\n" +
                "\t  \"id\": 1,\n" +
                "\t  \"name\" : \"India\",\n" +
                "\t  \"population\": \"1.4B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 2,\n" +
                "\t  \"name\" : \"China\",\n" +
                "\t  \"population\": \"1.3B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 3,\n" +
                "\t  \"name\" : \"USA\",\n" +
                "\t  \"population\": \"33B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 4,\n" +
                "\t  \"name\" : \"Japan\",\n" +
                "\t  \"population\": \"70B\"\n" +
                "\t} \n" +
                "  ]\n" +
                "}";

//        WireMock.stubFor(get("/countries")
//                .willReturn(aResponse()
//                        .withStatus(200)
////                        .withHeader("Content-Type",ContentType.JSON.toString())
//                        .withBody(responseBody))
//        );

    }

    @AfterAll
    public static void tearDown(){
        wireMockServer.shutdown();
    }

    @Given("I hit an api to get countries from wiremock server")
    public void getWiremockContent(){
        RestAssured.useRelaxedHTTPSValidation();
//        response = given()
//                .baseUri("http://localhost:8080")
//                .log().all()
//                .header("Accept", ContentType.JSON)
//                .when().get("/countries");
//        response.prettyPrint();

    }


    @Then("I verify the response of wiremock server")
    public void iVerifyTheResponseOfWiremockServer() {
    }
}
