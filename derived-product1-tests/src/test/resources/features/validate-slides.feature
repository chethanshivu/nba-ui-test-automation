@Regression
Feature: Validate slide details on the home page

  Scenario: Validate slide content, duration and count

    Given User is on the Website "https://www.nba.com/sixers/"
    Then Slides should be present below tickets menu
    And Number of slides should be 5 in home page
    And Slides title should have below content
    |76ers Take on Pacers Sunday Afternoon|
    |SIXERS FALL TO RAPTORS|
    |76ers Launch Enrich Program for Small Businesses|
    |Sixers Announce 2024-25 Theme Nights & Partner Activations|
    |Where Nick Nurse’s competitive nature was fueled. An in-depth look at the Iowa roots of the 76ers Head Coach.|