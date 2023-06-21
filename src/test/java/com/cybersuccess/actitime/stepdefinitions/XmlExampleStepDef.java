package com.cybersuccess.actitime.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class XmlExampleStepDef extends BaseClass{


    @Given("I get all contacts info from get all api")
    public void getAllContacts(){
        Header header1 = new Header("Accept", "application/xml");

        List<Header> headerList = new ArrayList<>();
        headerList.add(header1);

        Headers headers= new Headers(headerList);

        RestAssured.useRelaxedHTTPSValidation();
        response = given().baseUri("https://realestateautomation.agilecrm.com")
                .basePath("/dev/api/contacts")
                .headers(headers)
                .auth().basic("automation@yopmail.com", "2rm1jfdht83gov5qjbs7nbcckr")
                .when().log().all().get();
    }

    @Then("I verify first names in the response")
    public void iVerifyFirstNamesInTheResponse() {

//        response.prettyPrint();
        /*
        Get the content from response xml using xpath expression in following way
        @method: xmlPath() method returns reference of XmlPath class
        Then the getString method return the value in the form of String based on given xpath expression

        */
        String firstName = response.body().xmlPath().get("collection.contact[0].properties[0].value");

        System.out.println(firstName);


        //get first Names from all contacts
        int size =response.body().xmlPath().getList("collection.contact").size();

        System.out.println(size);

        for(int i=0; i<size;i++){
            String xpath = "collection.contact["+i+"].properties[0].value";
            String first_name  =response.body().xmlPath().getString(xpath);
            System.out.println(first_name);
        }

        //verify the first name from the contact using hamcrest library
        ValidatableResponse  response1  =  response.then();

        List<String> names = List.of("Pawan", "saurav");

        for(int i=0; i<size;i++){
            String xpath = "collection.contact["+i+"].properties[0].value";
            response1.body(xpath, containsString(String.valueOf(names)));
        }


        // response.then().body("collection.contact[0].properties[0].value", equalTo("Pawan"));

//        List<String> firstNameList =response.body().xmlPath().getList("collection.contact[1].properties[0].value");

        // hamcrest
//        System.out.println(firstNameList);
    }
}
