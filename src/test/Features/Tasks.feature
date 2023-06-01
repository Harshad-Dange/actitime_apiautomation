Feature: Verify tasks api

  @Task
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

  @CreateTask
  Scenario: Verify task is created with valid customer and project Id
    Given I set up the request structure to create customer payload
      | name                 | archived |
      | Test API Automation4 | false    |
    When I hit an api
      | method | endPoint  |
      | POST   | customers |
    Then I verify the status code as 200 and archived value in response
      | false |
    When I set up the request structure to create project
    And I hit an api
      | method | endPoint |
      | POST   | projects |
    Then I verify project is getting created successfully
    When I setup the request structure to create task
      | name          | status | typeOfWorkId | estimatedTime |
      | Api Test Task | open   | 2            | 123490        |
    And I hit an api
      | method | endPoint |
      | POST   | tasks    |
    Then I verify task is created successfully
      | name          | status | typeOfWorkId | estimatedTime |
      | Api Test Task | open   | 2            | 123490        |


