package StepDefinitions;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchFilterPage;
import utils.DriverFactory;

public class StepDefinitionForSearchFilter {
	WebDriver driver;
	SearchFilterPage SFP;
	@Given("I am on the booking website homepage")
	public void i_am_on_the_booking_website_homepage() {
	    // Write code here that turns the phrase above into concrete actions
		driver = DriverFactory.getDriver();
		SFP = new SearchFilterPage(driver);
        
		driver.get("https://www.booking.com/");
	}

	@Given("the search bar is displayed")
	public void the_search_bar_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(SFP.isSearchBarDisplayed(), "Search bar should be displayed");

	  
	}

	@Given("I want to search for hotels")
	public void i_want_to_search_for_hotels() {
	    // Write code here that turns the phrase above into concrete actions
		SFP.searchForHotels();
	}

	@When("I enter {string} in the destination field")
	public void i_enter_in_the_destination_field(String string) {
	    // Write code here that turns the phrase above into concrete actions
		SFP.enterDestination(string);
	  
	}

	
	@When("I select check-in date as {string} and select check-out date as {string}")
	public void i_select_check_in_date_as_and_select_check_out_date_as(String checkInDate, String checkOutDate) {
		  SFP.enterCheckInAndCheckOutDate(checkInDate, checkOutDate);
	}

	@When("I click the search button")
	public void i_click_the_search_button() {
	    // Write code here that turns the phrase above into concrete actions
		SFP.clickSubmit();
	   
	}

	@Then("I should see search results for {string}")
	public void i_should_see_search_results_for(String destination) {
	    // Write code here that turns the phrase above into concrete actions
	SFP.validationOfSearchResultsDisplayed(destination);
	}

	@Then("the results should display available hotels")
	public void the_results_should_display_available_hotels() {
	    // Write code here that turns the phrase above into concrete actions
		 SFP.verifyHotelResultsDisplayed();   
	}

	@When("I leave the destination field empty")
	public void i_leave_the_destination_field_empty() {
	    // Write code here that turns the phrase above into concrete actions
		  SFP.leaveDestinationFieldEmpty();
	  
	}

	@Then("I should see an error message {string}")
	public void i_should_see_an_error_message(String expectedMessage) {
		Assert.assertTrue(SFP.isErrorMessageDisplayed(expectedMessage),
                "Expected error message not displayed: " + expectedMessage);

	}

	

	@When("I leave the check-in date and leave check-out date empty")
	public void i_leave_the_check_in_date_and_leave_check_out_date_empty() {
		 System.out.println("check-in and check-out date as default.");
	}

	@Then("the search should be performed using default dates")
	public void the_search_should_be_performed_using_default_dates() {
	    // Write code here that turns the phrase above into concrete actions
		  System.out.println("Search with check-in and check-out date as default.");
	}

	
	@When("I sort the results by {string}")
	public void i_sort_the_results_by(String string) {
	    // Write code here that turns the phrase above into concrete actions
		 SFP.sortResultsByOption(string);
	  
	}
	@Then("the results should be sorted accordingly")
	public void the_results_should_be_sorted_accordingly() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Sorting is Done");
	}

}
