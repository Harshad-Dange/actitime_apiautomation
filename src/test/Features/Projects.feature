Feature: Verify all projects api

  @Projects
  Scenario: verify project information under the Big Bang Company customer
    Given I set up the request structure
      | customerIds | 6  |
      | limit       | 10 |
    When I hit an api
      | method | endPoint |
      | GET    | projects |
    Then I verify the status code as 200
    And I verify the project information in the response


    Scenario: Test
      Given  I set up the request structure to create customer



