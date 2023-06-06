@GetAllCustomer
Feature: Verify customer feature

  Scenario: Verify all customer information in get all customer api response
    Given I set up the request structure
      |||
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

  @CreateCustomer
  Scenario: Verify create customer using valid details
    Given I set up the request structure to create customer
      | name           | archived |
      | Cyber Success2 |          |
    When I hit an api
      | method | endPoint  |
      | POST   | customers |
    Then I verify customer is getting create succesfully with name "Cyber Success2"
    When I set up the request structure get customer information
    And I hit an api
      | method | endPoint  |
      | GET    | customers |


  Scenario: Verify customer should not be created with duplicate name
    Given I set up the request structure to create customer
      | name          | archived |
      | Cyber Success |          |
    When I hit an api
      | method | endPoint  |
      | POST   | customers |
    Then I verify the status code as 400 and error message
      | message | Customer with specified name already exists |

  @ErrorMessages
  Scenario Outline: Verify customer should not be created with missing name
    Given I set up the request structure to create customer
      | name   | archived |
      | <name> |          |
    When I hit an api
      | method | endPoint  |
      | POST   | customers |
    Then I verify the status code as 400 and error message
      | message | <errorMsg> |
    Examples:
      | name  | errorMsg                                |
      | empty | String length must be between 1 and 255 |
      |       | Mandatory field is not specified        |

    @ArchivedVerification
  Scenario: Verify customer should not be created with missing archived
    Given I set up the request structure to create customer payload
      | name           | archived |
      | API Automation1 |          |
    When I hit an api
      | method | endPoint  |
      | POST   | customers |
    Then I verify the status code as 200 and archived value in response
    |false|

#    1. Verify customer should not be created with invalid cred
#    2. Verify customer should not be created with invalid http method
#    3. Verify customer should not be created with invalid endpoint
#    4. Verify customer should not be created when hedders are missing
#    5. Verify customer should not be created when payload is missing







