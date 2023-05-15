package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PIMStepDefinition {

    @Then("I login to application with username {string} and password {string}")
    public void loginOrangeHrm(String username, String password){
        System.out.println("This is login method and user name is :"+ username + " and password is :" + password);
    }


    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication() {
        System.out.println("This is add emp method");
    }

    @Then("Verify employee added successfully")
    public void verifyEmployeeAddedSuccessfully() {
        System.out.println("This is verify emp method");
    }

    @When("I search the newly added with employee status filter")
    public void iSearchTheNewlyAddedWithEmployeeStatusFilter() {
        System.out.println("This is search emp method with filter");
    }

    @Then("I verify newly added emp in search result")
    public void iVerifyNewlyAddedEmpInSearchResult() {
        System.out.println("This is verify newly added emp method");
    }

    @Given("I select any filter from filter section")
    public void iSelectAnyFilterFromFilterSection() {
        System.out.println("This is select filter method");
    }

    @When("I click on Reset button")
    public void iClickOnResetButton() {
        System.out.println("This is reset button verify method");
    }

    @Then("I verify all filter should get reset")
    public void iVerifyAllFilterShouldGetReset() {
        System.out.println("This is verify all filter reset method");
    }
}
