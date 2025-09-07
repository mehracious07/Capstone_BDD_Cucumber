package pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchFilterPage {
	private WebDriver driver;
	By searchBoxpath = By.xpath("//div[@data-testid='searchbox-layout-wide']");
	By destinationField = By.xpath("//input[@name='ss' or placeholder=\"Where are you going?\" ]");
	By clearButton = By.xpath("//span[@aria-label='Clear']//*[name()='svg']");
	By dateInput = By.xpath("//button[@data-testid='searchbox-dates-container']");
	By searchButtonpath = By.xpath("//button[@type='submit']");
	By searchResultsHeadingLocator=By.xpath("//h1[@class='b87c397a13 cacb5ff522']");
	By hotelResultsLocator = By.xpath("//div[@data-testid='property-card']");
	By errorMessageLocator = By.xpath("//div[@class='b9b405fa52']");
	By dateClearButton = By.xpath("//span[@aria-label='Clear']//*[name()='svg']"); 
	By FilterButton =By.xpath("//span[@class='a9918d47bf']");
	By FilterOptions=By.xpath("//button[@role='option']");
	
	public SearchFilterPage(WebDriver driver) {
		this.driver=driver;
	}
//--------------------------------------------------------------------------------------------------------------->
	public boolean isSearchBarDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxpath));
	        return searchBox.isDisplayed();
	    } catch (Exception e) {
	        System.out.println("Search bar not found: " + e.getMessage());
	        return false;
	    }
	}

//--------------------------------------------------------------------------------------------------------------->	
	public void searchForHotels() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement destinationFieldInput = wait.until(ExpectedConditions.elementToBeClickable(destinationField));
       

	    try {
	        destinationFieldInput.click(); // Try regular click first
	    } catch (ElementClickInterceptedException e) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", destinationFieldInput);
	    }
	}

//--------------------------------------------------------------------------------------------------------------->
	public void enterDestination(String destination) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement destinationFieldInput = wait.until(ExpectedConditions.elementToBeClickable(destinationField));
	    
	    destinationFieldInput.click();

	  
	    @SuppressWarnings("deprecation")
		String currentValue = destinationFieldInput.getAttribute("value");
	    if (currentValue != null && !currentValue.isEmpty()) {
	        try {
	            WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(clearButton));
	            clearBtn.click();
	            System.out.println("Clear button clicked to reset destination field.");
	        } catch (Exception e) {
	            System.out.println("Clear button was not clickable or not found.");
	        }
	    }

	    // Now enter new destination
	    destinationFieldInput.sendKeys(destination);
	}



//--------------------------------------------------------------------------------------------------------------->	
	public void enterCheckInAndCheckOutDate(String checkInDate, String checkOutDate) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement dateInputField = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
		    dateInputField.click();

		    String checkInXPath = "//span[@data-date='" + checkInDate + "']";
		    String checkOutXPath = "//span[@data-date='" + checkOutDate + "']";

		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkInXPath)));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkOutXPath)));

		    WebElement checkInElement = driver.findElement(By.xpath(checkInXPath));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkInElement);
		    checkInElement.click();

		    WebElement checkOutElement = driver.findElement(By.xpath(checkOutXPath));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutElement);
		    checkOutElement.click();
	}
	
//--------------------------------------------------------------------------------------------------------------->	
	public void clickSubmit() {
		WebElement searchButton = driver.findElement(searchButtonpath);
		searchButton.click();
	}
	
//--------------------------------------------------------------------------------------------------------------->	
	public void validationOfSearchResultsDisplayed(String destination) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    try {
	        WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsHeadingLocator));
	        String headingText = headingElement.getText().toLowerCase();

	        if (!headingText.contains(destination.toLowerCase())) {
	            throw new AssertionError("Search result heading does not contain expected destination. " +
	                                     "Expected: " + destination + ", but found: " + headingText);
	        }

	        List<WebElement> hotelCards = driver.findElements(hotelResultsLocator);
	        if (hotelCards.isEmpty()) {
	            throw new AssertionError("No hotel results displayed for destination: " + destination);
	        }

	        System.out.println("Search results for '" + destination + "' validated successfully.");

	    } catch (Exception e) {
	        throw new AssertionError("Search results page did not load or elements not visible in time for destination: " + destination);
	    }
	}

//------------------------------------------------------------------------------------------------------------------>
	public void verifyHotelResultsDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        // Wait until at least one hotel result is visible
	        List<WebElement> hotelResults = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelResultsLocator));

	        if (hotelResults.isEmpty()) {
	            throw new AssertionError("No hotel results displayed.");
	        }

	        System.out.println("Hotel results are displayed: " + hotelResults.size());

	    } catch (Exception e) {
	        throw new AssertionError("Hotel results not found within the timeout.");
	    }
	}
//------------------------------------------------------------------------------------------------------------------------->
	public void leaveDestinationFieldEmpty() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    try {
	        WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(clearButton));
	        clearBtn.click();
	    } catch (Exception e) {
	        throw new RuntimeException("Clear button not found or not clickable", e);
	    }
	}
//------------------------------------------------------------------------------------------------------------------------->
public boolean isErrorMessageDisplayed(String expectedMessage) {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
	    return error.getText().trim().equalsIgnoreCase(expectedMessage);
}

//-------------------------------------------------------------------------------------------------------------------------->
public void sortResultsByOption(String sortOptionText) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(FilterButton));
    filter.click();

    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FilterOptions));

    for (WebElement option : options) {
        String optionText = option.getText().trim();
        if (optionText.equalsIgnoreCase(sortOptionText)) {
            option.click();
            return;
        }
    }

    throw new RuntimeException("Sort option not found: " + sortOptionText);
}

}


