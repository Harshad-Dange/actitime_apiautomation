Feature: Verify customer feature

  @GetAllCustomer
  Scenario: Verify all customer information in get all customer api response
    Given I set up the request structure to fetch all customer details
    When I hit an api
    Then I verify all customer details in the response