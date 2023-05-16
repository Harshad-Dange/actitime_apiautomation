package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.util.Map;

public class PIMStepDefinition {
    WebDriver driver;
    @Then("I login to application with username {string} and password {string}")
    public void loginOrangeHrm(String username, String password){
        System.out.println("This is login method and user name is :"+ username + " and password is :" + password);
    }


    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication(Map<String,String> empDetails) {
        System.out.println("This is add emp method");

//        System.out.println(empDetails.get("firstName"));
        System.out.println("Iterating empDetail through entrySet");

        for(Map.Entry<String,String> empObj: empDetails.entrySet()){
            System.out.println(empObj.getKey() + " : "+ empObj.getValue());

        }

        System.out.println("Iterating empDetail through forEach");
        empDetails.forEach((key,val)->{
            System.out.println(key+ " : "+ val);
        } );

         driver= new ChromeDriver();
        driver.findElement(By.xpath("enterFirstName")).sendKeys(empDetails.get("firstName"));
        driver.findElement(By.xpath("enterLastName")).sendKeys(empDetails.get("lastName"));
        if(empDetails.get("loginCredentials").equals("true")){
           driver.findElement(By.xpath("clickOnRadioButton")).click();
           driver.findElement(By.xpath("username")).sendKeys(empDetails.get("username"));
            driver.findElement(By.xpath("password")).sendKeys(empDetails.get("password"));
            driver.findElement(By.xpath("confirmPass")).sendKeys(empDetails.get("confirmPassword"));


        }

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

    @Then("I verify error messages on the screen")
    public void iVerifyErrorMessagesOnTheScreen(Map<String,String> errorMsg)  {

        String actualErrorMsg  = driver.findElement(By.xpath("error msg xpath")).getText();

        Assert.assertEquals(errorMsg.get("errorMsg"),actualErrorMsg);

    }
}
