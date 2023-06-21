package com.cybersuccess.actitime.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.*;

import static io.restassured.RestAssured.given;

public class FileDownloadStepDef extends  BaseClass{

    @Given("I download the file")
    public void fileDownload(){

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://reqres.in")
                .basePath("/img/faces")
                .header("Accept", ContentType.ANY)
                .log().all()
                .when().get("/7-image.jpg");
        response.prettyPrint();
    }


    @Then("I verify file is getting downloaded successfully")
    public void iVerifyFileIsGettingDownloadedSuccessfully() throws IOException {

        byte[] inputByteArray=  response.asByteArray();

        FileOutputStream outputStream = new FileOutputStream("/Users/harshad_dange/Documents/image.jpg");
        outputStream.write(inputByteArray);
        outputStream.flush();
        outputStream.close();
    }

    @Given("I download the file from github")
    public void iDownloadTheFileFromGithub() {
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://github.com")
                .basePath("/Harshad-Dange/actitime_apiautomation/raw/main")
                .header("Accept", ContentType.ANY)
                .log().all()
                .when().get("/pom.xml");
        response.prettyPrint();


    }

    @Then("I verify {string} file is getting downloaded")
    public void iVerifyFileIsGettingDownloaded(String fileName) throws IOException {

       InputStream inputStream=  response.asInputStream();

       FileOutputStream outputStream = new FileOutputStream("/Users/harshad_dange/Documents/"+ fileName);
        outputStream.write(inputStream.readAllBytes());
        outputStream.flush();
        outputStream.close();



    }
}
