@SampleExample
Feature: verify sample api response

  Scenario Outline: verify rick and morty api response
    Given I get the response from rick and morty api
      | status  | <status>  |
      | species | <species> |
      | gender  | <gender>  |
    Then I verify the response with status code 200
    Examples:
      | status  | species  | gender |
      | Alive   | Humanoid | Male   |
      | unknown |          | Female |
      | Alive   | Humanoid |        |


  Scenario: verify rick and morty api response
    Given I get the response from rick and morty api
      | name  | Rick |
    #Then I verify name in the response
    Then I verify the response with status code 200
