package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.security.SecureRandom;
import java.util.*;

public class ProjectStepDefinition extends BaseClass {
    String projectName;
    Map<String, Object>  createProjectBody;
    @Then("I verify the status code as {int}")
    public void verifyStatusCode(int expectedStatusCode){

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);

    }

    @And("I verify the project information in the response")
    public void iVerifyTheProjectInformationInTheResponse() {
       List<Map<String,Object>> itemsList = response.jsonPath().getList("items");
       // iterating the items list and verify customerId, name and archived files
        itemsList.forEach(map->{
              Assert.assertEquals(6, map.get("customerId")); // verify customer id should be 6
            Object name = map.get("name");
            Assert.assertTrue(Objects.nonNull(name));
            Assert.assertTrue(Objects.nonNull(map.get("archived")));
        });
        Assert.assertTrue(itemsList.size()<=10);
    }

    @When("I set up the request structure to create project")
    public void iSetUpTheRequestStructureToCreateProject(DataTable table) {

        projectName = new Faker().company().name();
        createProjectBody = new HashMap<>();
        createProjectBody.put("customerId", customerId);
//        createProjectBody.put("name", table.asMaps().get(0).get("name"));
        createProjectBody.put("description", "This is sample desc");
        createProjectBody.put("name", projectName);

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
                .body(createProjectBody)
                .log()
                .all();
    }

    @Then("I verify project is getting created successfully")
    public void iVerifyProjectIsGettingCreatedSuccessfully() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(projectName , response.jsonPath().getString("name"));
        Assert.assertEquals(customerId , response.jsonPath().getInt("customerId"));
        Assert.assertFalse(response.jsonPath().getBoolean("archived"));
        Assert.assertEquals(createProjectBody.get("description") , response.jsonPath().getString("description"));
        projectId = response.jsonPath().getInt("id");
    }
}
