package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.requestSpecification;

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

    @Given("I upload the file")
    public void iUploadTheFile() throws IOException {
        File file = new File("/Users/harshad_dange/Documents/test_data 2.csv");
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        Response response = requestSpecification.baseUri("https://postman-echo.com")
                .header("Accept", ContentType.JSON)
                .multiPart("file", file, ContentType.MULTIPART.toString())
                .log().all().post("/post");

        response.prettyPrint();

        //https://github.com/AntonOsika/gpt-engineer/raw/main/tests/__init__.py
        requestSpecification = RestAssured.given();
        response = requestSpecification.baseUri("https://github.com/")
                .basePath("AntonOsika/gpt-engineer/raw/main/tests/")
                .log().all()
                .get("test_db.py");

       byte[] byteArray = response.asByteArray();

        FileOutputStream outputStream= new FileOutputStream("/Users/harshad_dange/Documents/test_db.py");
        outputStream.write(byteArray);
        outputStream.flush();
        outputStream.close();

    }

    @Then("I verify employee added successfully in the system")
    public void iVerifyEmployeeAddedSuccessfullyInTheSystem() {
    }

    @And("I update the employee information")
    public void iUpdateTheEmployeeInformation() {
    }

    @And("I search the updated employee")
    public void iSearchTheUpdatedEmployee() {
    }

    @Then("I delete the employee")
    public void iDeleteTheEmployee() {
    }
}
