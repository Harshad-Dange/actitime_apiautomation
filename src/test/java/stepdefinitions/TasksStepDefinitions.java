package stepdefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class TasksStepDefinitions extends BaseClass{

    @Then("I verify all tasks info in the response")
    public void verifyResponse(){
        //verify the status code of a response
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Then("I verify all tasks for project id {int}")
    public void iVerifyAllTasksForProjectId(int taskId) {
        List<Integer> projectId = response.jsonPath().getList("items.projectId");

        //Assert the execution if project id does not present in the response
        projectId.forEach(id-> Assert.assertEquals(taskId, (int) id));
    }
}
