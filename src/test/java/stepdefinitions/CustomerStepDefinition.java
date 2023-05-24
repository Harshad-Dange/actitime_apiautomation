package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class CustomerStepDefinition {

    // Webdriver driver;
    RequestSpecification requestSpecification;
    Response response;

    @Given("I set up the request structure to fetch all customer details")
    public void setup(){
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept","application/json")
                .log()
                .all();
    }

    @When("I hit an api")
    public void iHitAnApi() {
        //hit an api and get the response
         response= requestSpecification.get("/customers");
         response.prettyPrint(); // print the response in pretty format

    }

    @Then("I verify all customer details in the response")
    public void iVerifyAllCustomerDetailsIntTheResponse() {

        int actualStatusCode =    response.getStatusCode();
        Assert.assertEquals(200,actualStatusCode);

    }
}
