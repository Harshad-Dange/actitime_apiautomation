package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.Assert;
import pojo.UserTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class UsersStepDefinition extends BaseClass{

    JSONObject createUserPayload;
    UserTypes userTypes;
    @Given("I setup the request structure to create user")
    public void createUser(){
        createUserPayload = new JSONObject();
        String firstName = new Faker().name().firstName();
        createUserPayload.put("email", firstName+ "@yopmail.com");
        createUserPayload.put("firstName", firstName);
        createUserPayload.put("lastName", new Faker().name().lastName());
        createUserPayload.put("username", firstName);
        createUserPayload.put("password", "12345!@#$%");

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(createUserPayload.toString())
                .log()
                .all();


    }

    @Then("I verify user is getting created successfully")
    public void iVerifyUserIsGettingCreatedSuccessfully() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(createUserPayload.get("firstName"), response.jsonPath().getString("firstName"));
        Assert.assertTrue(Objects.isNull(response.jsonPath().getString("password")));
    }

    @Given("I setup the request structure to create user using json file")
    public void iSetupTheRequestStructureToCreateUserUsingJsonFile() throws FileNotFoundException {

        String jsonFIlePath = "src/test/resources/CreateUserPayload.json";

        FileReader reader = new FileReader(new File(jsonFIlePath));
//        reader.read()

        byte[]  payload;
        try {
            payload=  Files.readAllBytes(Path.of(jsonFIlePath)); // access the content from json file convert into byte array
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .log()
                .all();
    }

    @Given("I setup the request structure to create user using class object")
    public void iSetupTheRequestStructureToCreateUserUsingClassObject() {
        String firstName = new Faker().name().firstName();
        userTypes = new UserTypes();
        userTypes.setFirstName(firstName);
        userTypes.setLastName(new Faker().name().lastName());
        userTypes.setUsername(firstName);
        userTypes.setEmail(firstName + "@gmail.com");
        userTypes.setPassword("1234556");


        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(userTypes)
                .log()
                .all();

    }

    @Then("I verify user is getting created successfully using class object")
    public void iVerifyUserIsGettingCreatedSuccessfullyUsingClassObject() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(userTypes.getFirstName(), response.jsonPath().getString("firstName"));
        // rest all assertion will be in same way.....
    }
}
