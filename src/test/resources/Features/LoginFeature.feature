Feature: Login Feature

  Scenario: Verify user is able to login to application with valid credentials
    Given I login to application with valid credentials
    Then I verify user is logged in to application successfully
