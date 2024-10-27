@Regression
Feature: Duplicate hyperlinks

  Scenario: find duplicate hyperlinks in a given website footer

    Given User is on the Website "https://www.nba.com/bulls"
    When User scrolls down to the footer
    Then Different hyperlinks should be available
    And Capture all the links and store in csv file
    And Add the duplicated hyperlinks to "DuplicateHyperlinks" csv file
