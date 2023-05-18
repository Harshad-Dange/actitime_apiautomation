Feature: Verify recruitment features

  Background: login to application
    Given I launch the "chrome" browser
    Then I enter username "admin" and password "admin123" and perform login

  @Tag1
  Scenario: verify add candidate functionality in the recruitment module
    Given I add candidate in the system
      | firstName | lastName | emailId         | contactNumber |
      | cyber     | success  | cs@yopmail.com  | 1234567       |
      | cyber1    | success1 | cs1@yopmail.com | 12345678      |
    Then I verify candidate added successfully by searching name of candidate in filter
    When I update the interview status of newly added candidate
      | passed |
    Then I verify the interview status getting updated successfully

  Scenario: Verify filter
    Given I select any filter from filter section
#    |firstName , lastName  , empStatus, empId,username|
      | firstName |
      | lastName  |
      | empStatus |
      | empId     |
      | username  |
    When I click on Reset button
    Then I verify all filter should get reset