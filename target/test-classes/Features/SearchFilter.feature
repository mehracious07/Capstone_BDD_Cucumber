Feature: Hotel Search Functionality
  As a traveler
  I want to search for hotels
  So that I can find accommodation for my trip

  Background:
    Given I am on the booking website homepage
    And the search bar is displayed

  @smoke @positive
  Scenario: Search hotels with valid destination and dates
    Given I want to search for hotels
    When I enter "Paris" in the destination field
    And I select check-in date as "2025-10-01" and select check-out date as "2025-10-05"
    And I click the search button
    Then I should see search results for "Paris"
    And the results should display available hotels
    When I sort the results by "Price (lowest first)"
    Then the results should be sorted accordingly
 

  @negative @validation
  Scenario: Search hotels with empty destination
    Given I want to search for hotels
    When I leave the destination field empty
    And I select check-in date as "2025-10-01" and select check-out date as "2025-10-05"
    And I click the search button
    Then I should see an error message "Enter a destination to start searching."
  

  @negative @validation
  Scenario: Search hotels with empty dates
    Given I want to search for hotels
    When I enter "Paris" in the destination field
    And I leave the check-in date and leave check-out date empty
    And I click the search button
    Then the search should be performed using default dates
    And I should see search results for "Paris"
    And the results should display available hotels