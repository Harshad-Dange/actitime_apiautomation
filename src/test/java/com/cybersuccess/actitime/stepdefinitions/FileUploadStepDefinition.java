package com.cybersuccess.actitime.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.io.File;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class FileUploadStepDefinition extends  BaseClass {
    File file;
    @Given("I prepare the request structure to upload the file")
    public void fileUpload(){
       String filePath = "/Users/harshad_dange/Documents/ZipFile.zip";

       file = new File(filePath);

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://postman-echo.com")
                .header("Content-Type", ContentType.MULTIPART.toString())
                .multiPart("file", file)
                .log().all()
                .when().post("/post");
        response.prettyPrint();
    }


    @Then("I verify file is getting uploaded successfully")
    public void iVerifyFileIsGettingUploadedSuccessfully() {

        // verify status code
        Assert.assertEquals(200, response.getStatusCode());

        // get the files field data
        Map<String, String> filesObject = response.jsonPath().getMap("files");

        // get all the keys from filesObject
        Set<String> keys=filesObject.keySet();

        Object fileName = keys.toArray()[0];

        String actualFileName = String.valueOf(fileName);

        System.out.println(actualFileName);

        System.out.println(file.getName());

        Assert.assertEquals(file.getName(), actualFileName);


    }
}
