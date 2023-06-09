Feature: verify sample api response

  @SampleExample
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

