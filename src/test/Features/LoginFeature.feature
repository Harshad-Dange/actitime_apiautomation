Feature: Login Feature

  Scenario: Verify user is able to login to application with valid credentials
    Given I login to application with valid credentials
    Then I verify user is logged in to application successfully
      | test1 |
      | test2 |
      | test3 |

#  Scenario: verify login feature with invalid credentials
#    Given I launch the "chrome" browser
#    When I enter username "admin" and password "24345@#@" and perform login
#    Then I verify user is logged in to application successfully


  Scenario Outline: verify login feature with invalid credentials
    Given I launch the "chrome" browser
    When I enter username "<username>" and password "<password>" and perform login
    Then I verify user is logged in to application successfully
    Examples:
      | browser | username | password |
      | chrome  | admin    | 1234     |
      | chrome  | 1234     | admin123 |
      | chrome  | null     | admin123 |
      | chrome  | admin    |          |


  Scenario Outline: verify login feature with invalid credentials
    Given I launch the "chrome" browser
    When I enter username "<username>" and password "<password>" and perform login
    When I enter below credentials
      | username | <username> |
      | password | <password> |
    Then I verify user is logged in to application successfully
    Examples:
      | browser | username | password |
      | chrome  | admin    | 1234     |
      | chrome  | 1234     | admin123 |
      | chrome  | null     | admin123 |
      | chrome  | admin    |          |