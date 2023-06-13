Feature: Verify WorkflowStatus feature

  @CreateWorkflow
  Scenario: verify workflow status create
    Given I create a workflow status
      | type | completed |
    Then I verify workflow status is getting created
    When I get all workflow statuses
    Then I verify newly created workflow status in the response


    @SortWorkFlowResponse
    Scenario: verify default sorting order for get workflowStatuses api
      Given I hit an api to get all workflowStatuses
      Then I verify the response is in ascending order by id