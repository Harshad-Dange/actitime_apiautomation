Feature: Login Feature

  Scenario: Verify user is able to login to application with valid credentials
    Given I login to application with valid credentials
    Then I verify user is logged in to application successfully


  Scenario: verify login feature with invalid credentials
    Given I launch the "chrome" browser
    When I enter username "admin" and password "24345@#@" and perform login
    Then I verify user is logged in to application successfully