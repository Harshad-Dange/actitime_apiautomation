Feature: Verify the PIM module

  Background: Login to application
    Given I launch the "chrome" browser
    Then I login to application with username "admin" and password "admin1234"

  Scenario: Verify add emp feature
    Given I add employee in the application
    Then Verify employee added successfully

  Scenario: Verify employee search with Employee Status filter
    Given I add employee in the application
    When I search the newly added with employee status filter
    Then I verify newly added emp in search result

  Scenario: Verify reset filter
    Given I select any filter from filter section
    When I click on Reset button
    Then I verify all filter should get reset


