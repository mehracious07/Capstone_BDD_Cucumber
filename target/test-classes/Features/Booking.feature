Feature: End-to-End Hotel Booking
  As a traveler
  I want to search and book a hotel room
  So that I can complete my trip planning online

  Background:
    Given I am on the booking website homepage
    And the search bar is displayed

  @smoke @positive @endtoend
  Scenario: Complete hotel booking with valid details
    # Hotel Search (reusing existing steps)
    Given I want to search for hotels
    When I enter "New Delhi" in the destination field
    And I select check-in date as "2025-10-01" and select check-out date as "2025-10-05"
    And I click the search button
    Then I should see search results for "New Delhi"
    And the results should display available hotels
   

    # Room Selection
    When I select the first hotel from the results
    And I select one room
    And I click on the Reserve button
    Then I should see the booking summary page with the selected room details

    # Guest Details
    When I enter guest firstName "Sanjay"
    And I enter guest lastName "Mehara"
    And I enter email "mehracious619@gmail.com"
    And I enter phone number "7452896780"
    When I click Final Details button and proceed to payment page

    
    # Valid Payment
    And I enter card number "4111111111111111"
    And I enter expiry date "12/28"
    And I enter CVV "123"
    And I confirm the payment
    Then the payment should be processed successfully

    # Booking Confirmation
    And I should see a booking confirmation page with booking ID
    And the confirmation should display hotel name, room type, dates, and total amount
    And I should receive a confirmation email at "john.doe@example.com"
