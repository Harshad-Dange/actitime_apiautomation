Feature: Department

  @Department
  Scenario: Test
    Given I setup the request structure to get all department
    When I hit an api
      | method | endPoint  |
      | GET    | departments |
    Then I verify get all department details
