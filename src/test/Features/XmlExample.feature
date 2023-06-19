Feature: XML response verification

@XmlVerification
  Scenario: Get  first name from get all contacts api
    Given I get all contacts info from get all api
    Then I verify first names in the response
