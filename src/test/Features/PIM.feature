Feature: Verify the PIM module

#  Background: Login to application
#    Given I launch the "chrome" browser
#    Then I login to application with username "admin" and password "admin1234"
#
#  Scenario: Verify add emp feature
#    Given I add employee in the application
#    Then Verify employee added successfully

  Scenario Outline: Verify add emp feature with invalid details
    Given I add employee in the application
      | firstName        | <firstName>        |
      | lastName         | <lastName>         |
      | empId            | <empId>            |
      | loginCredentials | <loginCredentials> |
      | username         | <username>         |
      | password         | <password>         |
      | confirmPassword  | <confirmPassword>  |
    #Map<String,String>
    Then I verify error messages on the screen
      | errorMsg | <errorMsg> |
    Examples:
      | firstName | lastName | empId | loginCredentials | username     | password  | confirmPassword | errorMsg                          |
      |           | test     | 001   | flase            |              |           |                 | Required                          |
      | test      |          | 001   | flase            |              |           |                 | Required                          |
      |           |          | 001   | flase            |              |           |                 | Required                          |
      | cyber     | success  |       | true             |              | Test1234  | Test1234        | Required                          |
      | cyber     | success  | 001   | true             | cbersucccess |           |                 | Required                          |
      | cyber     | success  | 001   | true             | cbersucccess | Test123   |                 |                                   |
      | cyber     | success  | 001   | true             | cbersucccess | Test12345 | Test123456      | Passwords do not match            |
      | cyber     | success  | 001   | true             | cbersucccess | Test      | Test            | Should have at least 7 characters |


#  Scenario: Verify employee search with Employee Status filter
#    Given I add employee in the application
#    When I search the newly added with employee status filter
#    Then I verify newly added emp in search result
#
#  Scenario: Verify reset filter
#    Given I select any filter from filter section
#    When I click on Reset button
#    Then I verify all filter should get reset


