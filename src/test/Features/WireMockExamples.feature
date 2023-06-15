Feature: verify wiremock content

  @mockServer
  Scenario: verify response of get method from wiremock server
    Given I hit an api to get countries from wiremock server
    Then I verify the response of wiremock server