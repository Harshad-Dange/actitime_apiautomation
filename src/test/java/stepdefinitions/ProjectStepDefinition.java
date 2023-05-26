package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProjectStepDefinition extends BaseClass {

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
}
