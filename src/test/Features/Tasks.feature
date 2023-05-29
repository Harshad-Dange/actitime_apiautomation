Feature: Verify tasks api

  Scenario: Verify get all task api
    Given I set up the request structure
    When I hit an api
      | method | endPoint |
      | GET    | tasks    |
    Then I verify all tasks info in the response

  Scenario: Verify all task for project id 23
    Given I set up the request structure
      | projectIds | 23 |
    When I hit an api
      | method | endPoint |
      | GET    | tasks    |
    Then I verify all tasks for project id 23