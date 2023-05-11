package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginStepDefinition {
    @Given("I login to application with valid credentials")
    public void performLogin(){
        //launch browser
        //Navigate to website
        //perform login
    }

    @Then("I verify user is logged in to application successfully")
    public void iVerifyUserIsLoggedInToApplicationSuccessfully() {

        //verify login successfully
    }
}
