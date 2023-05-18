package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class RecruitmentStepDef {
    // DataTable is class which represent the data mentioned in the feature file
    WebDriver driver;
    @Before
    public void before(){
        System.out.println("This is before scenario hook");

        //WebDriver driver = new ChromeDriver();

    }
    @After
    public void after(){
        System.out.println("This is after scenario hook");
        //logic to close the browser
    }

//    @BeforeStep
//    public void beforeStep(){
//        System.out.println("This is before step");
//    }
//
//    @AfterStep
//    public void afterStep(){
//        System.out.println("This is after step");
//    }

    @Before("@Tag1")
    public void beforeHook(){
        System.out.println("This is before conditional(Tag) hook");
    }
    @After("@Tag1")
    public void afterTagHook(){
        System.out.println("This is after conditional(Tag) hook");
    }

    @BeforeAll
    public static void beforeAllHook(){
        System.out.println("This is before all hook");
    }

    @AfterAll
    public static void afterAllHook(){
        System.out.println("This is after all hook");
    }

    @Given("I add candidate in the system")
    public void addCandidate(DataTable table) {
        //logic to add candidate
        System.out.println("This is add candidate method");

        // get the data from table variable
        List<Map<String,String>> list = table.asMaps(); // this returns List<Map<String,String>>

        //print the size of list
        System.out.println(list.size());

        //iterate the content from list
        for( Map<String,String> map: list){

            //print the data present in map
            for(Map.Entry<String,String> data:map.entrySet()){
                System.out.println("Key: "+ data.getKey()+ " value: " + data.getValue());
            }
        }
    }

    @Then("I verify candidate added successfully by searching name of candidate in filter")
    public void verifyAddCandidate() {
        System.out.println("This is verify candidate method");
    }

    @When("I update the interview status of newly added candidate")
    public void iUpdateTheInterviewStatusOfNewlyAddedCandidate(String status) {
        System.out.println("this is update interview status method");

    }

    @Then("I verify the interview status getting updated successfully")
    public void iVerifyTheInterviewStatusGettingUpdatedSuccessfully() {
        System.out.println("This is verify interview status method");
    }
}
