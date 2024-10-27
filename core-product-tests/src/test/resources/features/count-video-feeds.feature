@Regression
Feature: Count total video feeds

  Scenario: Count total video feeds and video feeds added 3 days back

    Given User is on the Website "https://www.nba.com/warriors"
    And User hover over on the menu "..." item
    And User clicks on "News & features"
    When Fetch the total number of video feeds
    And Fetch the total video feeds added 3 days back
