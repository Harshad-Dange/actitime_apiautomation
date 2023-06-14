Feature: Verify the PIM module

#  Background: Login to application
#    Given I launch the "chrome" browser
#    Then I login to application with username "admin" and password "admin1234"
#
#  Scenario: Verify add emp feature
#    Given I add employee in the application
#    Then Verify employee added successfully

#  Scenario Outline: Verify add emp feature with invalid details
#    Given I add employee in the application
#      | firstName        | <firstName>        |
#      | lastName         | <lastName>         |
#      | empId            | <empId>            |
#      | loginCredentials | <loginCredentials> |
#      | username         | <username>         |
#      | password         | <password>         |
#      | confirmPassword  | <confirmPassword>  |
#    #Map<String,String>
#    Then I verify error messages on the screen
#      | errorMsg | <errorMsg> |
#    Examples:
#      | firstName | lastName | empId | loginCredentials | username     | password  | confirmPassword | errorMsg                          |
#      |           | test     | 001   | flase            |              |           |                 | Required                          |
#      | test      |          | 001   | flase            |              |           |                 | Required                          |
#      |           |          | 001   | flase            |              |           |                 | Required                          |
#      | cyber     | success  |       | true             |              | Test1234  | Test1234        | Required                          |
#      | cyber     | success  | 001   | true             | cbersucccess |           |                 | Required                          |
#      | cyber     | success  | 001   | true             | cbersucccess | Test123   |                 |                                   |
#      | cyber     | success  | 001   | true             | cbersucccess | Test12345 | Test123456      | Passwords do not match            |
#      | cyber     | success  | 001   | true             | cbersucccess | Test      | Test            | Should have at least 7 characters |


#  Scenario: Verify employee search with Employee Status filter
#    Given I add employee in the application
#    When I search the newly added with employee status filter
#    Then I verify newly added emp in search result
#
#  Scenario: Verify reset filter
#    Given I select any filter from filter section
#      | firstName,lastName,empStatus,empId,username |
#    When I click on Reset button
#    Then I verify all filter should get reset

  @Color2
  Scenario: Verify reset filter
    Given I select any filter from filter section
#    |firstName , lastName  , empStatus, empId,username|
      | firstName |
      | lastName  |
      | empStatus |
      | empId     |
      | username  |
    When I click on Reset button
    Then I verify all filter should get reset

    ## This hook will considered as a Tag as well as its been treated as a conditional hook
    ## Color1 is a tag hook, because it has a dedicated hook (@Before/@After) and also treated as Tag
    ## Color2 is a only tag because it does not have any dedicated hook
  @Color1
  Scenario: Sample1
    Given I take different colors as a input
      | Red  ,   Black |
      | Black ,  Green |
      | Yellow , Brown |
      | Pink  ,  White |

  @Color2
  Scenario: Sample2
    Given I take different colors as a input
      | Red    |
      | Black  |
      | Yellow |
      | Pink   |


  @AddEmp
  Scenario Outline: Verify add emp feature with invalid details
    Given I add employee in the application
      | firstName   | lastName   | empId   | loginCredentials   | username   | password   | confirmPassword   |
      | <firstName> | <lastName> | <empId> | <loginCredentials> | <username> | <password> | <confirmPassword> |
#    Then I verify error messages on the screen
#      | errorMsg | <errorMsg> |
    Examples:
      | firstName           | lastName | empId | loginCredentials | username     | password  | confirmPassword | errorMsg                          |
      | test,qtee,1323,3232 | test     | 001   | flase            |              |           |                 | Required                          |
      | test                |          | 001   | flase            |              |           |                 | Required                          |
      |                     |          | 001   | flase            |              |           |                 | Required                          |
      | cyber success       | success  |       | true             |              | Test1234  | Test1234        | Required                          |
      | cyber               | success  | 001   | true             | cbersucccess |           |                 | Required                          |
      | cyber               | success  | 001   | true             | cbersucccess | Test123   |                 |                                   |
      | cyber               | success  | 001   | true             | cbersucccess | Test12345 | Test123456      | Passwords do not match            |
      | cyber               | success  | 001   | true             | cbersucccess | Test      | Test            | Should have at least 7 characters |


  Scenario: Verify Employee CRUD operation
    Given I add 3 employee in the application
    Then I verify employee added successfully in the system
    When I search the newly added with employee status filter
    And I update the employee information
    And I search the updated employee
    Then I delete the employee


    @upload
    Scenario: Upload file
      Given I upload the file


