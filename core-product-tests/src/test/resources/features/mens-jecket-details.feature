@Regression
Feature: Fetch the details of men's jacket

  Scenario: Create text file with men's jacket title, price, and top seller message details

    Given User is on the Website "https://www.nba.com/warriors"
    And User hover on the Shop icon
    And User selects the mens icon
    Then User should be navigated to the "Men's Golden State Warriors Gear, Mens Warriors Apparel, Guys Clothing" page
    And User selects the jackets under filter
    And Fetch every jackets information and store to a text file
