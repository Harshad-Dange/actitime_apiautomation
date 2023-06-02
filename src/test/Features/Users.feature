Feature: Verify users features

  @CreateUser  @JSONObject
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
     # Then I verify user is getting created successfully


  @ClassObject
  Scenario: Scenario to create user with class object
    Given I setup the request structure to create user using class object
    When I hit an api
      | method | endPoint |
      | POST   | users    |
     Then I verify user is getting created successfully using class object







