Feature: verify file upload

@UploadFIle
  Scenario: verify file is getting uploaded successfully
    Given I prepare the request structure to upload the file
    Then I verify file is getting uploaded successfully