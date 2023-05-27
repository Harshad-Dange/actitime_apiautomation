@GetAllCustomer
Feature: Verify customer feature

  Scenario: Verify all customer information in get all customer api response
    Given I set up the request structure
    When I hit an api
      | method | endPoint  |
      | GET    | customers |
    Then I verify all customer details in the response
      | Joda Consulting Inc |

  Scenario: Verify customer information in get by id api
    Given I set up the request structure
    When I hit an api
      | method | endPoint  | pathParam |
      | GET    | customers | 6         |


    @CustomerSorting
  Scenario Outline: verify customer response is getting sorted in asc and desc order by name
    Given I set up the request structure
      | sort | <value> |
    When I hit an api
      | method | endPoint  |
      | GET    | customers |
    Then I verify customer response is getting sorted in "<order>" order
    Examples:
      | value | order |
      | -name | desc  |
      | name  | asc   |
