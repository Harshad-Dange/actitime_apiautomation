Feature: Verify users features

  @JSONObject
  Scenario: Verify user is getting created with all valid details
    Given I setup the request structure to create user
    When I hit an api
      | method | endPoint |
      | POST   | users    |
    Then I verify user is getting created successfully

  @JSONFile
  Scenario: Scenario to create user with json file
    Given I setup the request structure to create user using json file
    When I hit an api
      | method | endPoint |
      | POST   | users    |
      Then I verify user is getting created successfully


  @ClassObject
  Scenario: Scenario to create user with class object
    Given I setup the request structure to create user using class object
    When I hit an api
      | method | endPoint |
      | POST   | users    |
    Then I verify user is getting created successfully using class object


  @CreateUser
  Scenario: verify user update feature for gorest
    Given I setup the request structure to create user in gorest
      | name   | gender | email  | status |
      | random | male   | random | active |
    When I hit an api
      | method | endPoint |
      | POST   | users    |
    Then I verify user api response with status code 201
    When I update the newly created user
      | name     | gender | email  | status | pathParam |
      | previous | female | random | active | userId    |
    And I hit an api
      | method | endPoint | pathParam |
      | PUT    | users    | userId    |
    Then I verify user api response with status code 200







