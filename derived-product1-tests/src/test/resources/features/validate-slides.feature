@Regression
Feature: Validate slide details on the home page

  Scenario: Validate slide content, duration and count

    Given User is on the Website "https://www.nba.com/sixers/"
    Then Slides should be present below tickets menu
    And Fetch the number of slides in home page