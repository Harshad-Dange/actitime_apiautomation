package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PIMStepDefinition {
    WebDriver driver;
    @Then("I login to application with username {string} and password {string}")
    public void loginOrangeHrm(String username, String password){
        System.out.println("This is login method and user name is :"+ username + " and password is :" + password);
    }

/*    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication(Map<String,String> empDetails) {
        System.out.println("This is add emp method");

//        System.out.println(empDetails.get("firstName"));
        System.out.println("Iterating empDetail through entrySet");

        for(Map.Entry<String,String> empObj: empDetails.entrySet()){
            System.out.println(empObj.getKey() + " : "+ empObj.getValue());

        }*/
        @Given("I add employee in the application")
        public void iAddEmployeeInTheApplication(DataTable empDetails) {
           List<Map<String,String>> data= empDetails.asMaps();
            Map<String,String> map= data.get(0);
            System.out.println(map.get("firstName"));
            System.out.println(map.get("empId"));

            System.out.println(empDetails.asList());
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
    public void iSelectAnyFilterFromFilterSection(List<String> filters) {
        System.out.println("This is select filter method");
        System.out.println(filters);
//        String [] filter = filters.split(",");
//        for (String var :filter){
//            System.out.println(var);
//        }
//     Arrays.stream(filter).forEach(val-> System.out.println(val));
        for (String val: filters){
            System.out.println(val);
        }
        filters.forEach(val-> System.out.println(val));

    }

    @When("I click on Reset button")
    public void iClickOnResetButton() {
        System.out.println("This is reset button verify method");
    }

    @Then("I verify all filter should get reset")
    public void iVerifyAllFilterShouldGetReset() {
        System.out.println("This is verify all filter reset method");
    }

    @Then("I verify error messages on the screen")
    public void iVerifyErrorMessagesOnTheScreen(Map<String,String> errorMsg)  {

        String actualErrorMsg  = driver.findElement(By.xpath("error msg xpath")).getText();

        Assert.assertEquals(errorMsg.get("errorMsg"),actualErrorMsg);

    }

    @Given("I add {int} employee in the application")
    public void iAddEmployeeInTheApplication(int count) {
            for(int i=0; i<count; i++){

                //add employee logic

            }
    }

    @Before("@Color1")
    public void beforeTag(){
        System.out.println("This is conditional tag hook for color scenario");
    }

    @Given("I take different colors as a input")
    public void iTakeDifferentColorsAsAInput(DataTable colors) {

        System.out.println(colors.asList());

        System.out.println(colors.asLists());



    }
}
