Feature: verify file download


  Scenario: verify file is getting downloaded for reqres api using bytearray
    Given I download the file
    Then  I verify file is getting downloaded successfully
  @FileDownload
  Scenario: verify file download using inputstream
    Given I download the file from github
    Then I verify "pom.xml" file is getting downloaded