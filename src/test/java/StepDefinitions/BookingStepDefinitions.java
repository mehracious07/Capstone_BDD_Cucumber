package StepDefinitions;

import org.testng.Assert;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookingPage;
import utils.DriverFactory;


public class BookingStepDefinitions {
	WebDriver driver;
	BookingPage B;
	
	@When("I select the first hotel from the results")
	public void i_select_the_first_hotel_from_the_results() {
	    // Write code here that turns the phrase above into concrete actions
		driver = DriverFactory.getDriver();
		B = new BookingPage(driver); 
	    System.out.println("Selecting First Hotel");
	    B.selectHotel();
	    String originalWindow = driver.getWindowHandle();
	    for (String windowHandle : driver.getWindowHandles()) {
	        if (!windowHandle.equals(originalWindow)) {
	            driver.switchTo().window(windowHandle);
	            break;
	        }
	    }

	    System.out.println("Switched to the hotel details tab");
	}

	@When("I select one room")
	public void i_select_one_room() {
	    // Write code here that turns the phrase above into concrete actions
	   B.selectRoom();
	}
	
	@When("I click on the Reserve button")
	public void i_click_on_the_reserve_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	  B.clickReserveButton();
	}

	@Then("I should see the booking summary page with the selected room details")
	public void i_should_see_the_booking_summary_page_with_the_selected_room_details() {
	    // Write code here that turns the phrase above into concrete actions
		 // Write code here that turns the phrase above into concrete actions
		    boolean isSummaryDisplayed = B.isBookingSummaryDisplayed();
		    Assert.assertTrue(isSummaryDisplayed, "Booking summary page is not displayed!");


		    System.out.println("Booking summary page displayed"); 
	}

	@When("I enter guest firstName {string}")
	public void i_enter_guest_firstName(String string) {
	   B.enterFirstName(string);
	}
	
	@When("I enter guest lastName {string}")
	public void i_enter_guest_lastName(String string) {
	   B.enterLastName(string);
	}

	@When("I enter email {string}")
	public void i_enter_email(String string) {
	    // Write code here that turns the phrase above into concrete actions
	  B.enterEmailId(string);
	}

	@When("I enter phone number {string}")
	public void i_enter_phone_number(String string) {
	    // Write code here that turns the phrase above into concrete actions
	  B.enterPhoneNo(string);
	}

	@When("I click Final Details button and proceed to payment page")
	public void i_click_final_details_button_and_proceed_to_payment_page() {
	    B = new BookingPage(DriverFactory.getDriver());  
	    B.toPaymentPage();
	}




	@When("I enter card number {string}")
	public void i_enter_card_number(String string) {
	 System.out.println("Card no :"+ string);
	 }

	@When("I enter expiry date {string}")
	public void i_enter_expiry_date(String string) {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("Expiry :"+ string);
	}

	@When("I enter CVV {string}")
	public void i_enter_cvv(String string) {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("CVV no :"+ string);
	}

	@When("I confirm the payment")
	public void i_confirm_the_payment() {
	    // Write code here that turns the phrase above into concrete actions
	  B.initiateBooking();
	}

	@Then("the payment should be processed successfully")
	public void the_payment_should_be_processed_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@Then("I should see a booking confirmation page with booking ID")
	public void i_should_see_a_booking_confirmation_page_with_booking_id() {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@Then("the confirmation should display hotel name, room type, dates, and total amount")
	public void the_confirmation_should_display_hotel_name_room_type_dates_and_total_amount() {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@Then("I should receive a confirmation email at {string}")
	public void i_should_receive_a_confirmation_email_at(String string) {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@Given("I have selected a hotel room and entered guest details")
	public void i_have_selected_a_hotel_room_and_entered_guest_details() {
	    // Write code here that turns the phrase above into concrete actions
		
	}

	@Then("the booking should not be confirmed")
	public void the_booking_should_not_be_confirmed() {
	    // Write code here that turns the phrase above into concrete actions
	  
	}
	
	@Then("I should see an payment error message {string}")
	public void i_should_see_an_payment_error_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
}
