package pages;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.aventstack.extentreports.util.Assert;
import org.testng.Assert;

//import org.openqa.selenium.support.ui.Select;
public class BookingPage {
	private WebDriver driver;
	By hotelLocator = By.xpath("//div[@data-testid='property-card']");
    By roomCount = By.xpath("//span[@class='hprt-roomtype-icon-link ']");
	By reserveButton = By.xpath("//button[@id='hp_book_now_button']");
	By reserveButton2 = By.xpath("//button[contains(@class,'hp_rt_input px--fw-cta js-reservation-button')]");
	By bookingSummary = By.xpath("//div[@class='bui-group bui-group--large']");
    By firstName = By.xpath("//input[@data-testid='user-details-firstname' ]");
    By lastName = By.xpath("//input[@data-testid='user-details-lastname' ]");
    By emailInput = By.xpath("//input[@data-testid='user-details-email' ]");
    By toPaymentButton=By.xpath("//button[@name='book']");
    By cardNoInput=By.xpath("//input[@id='pc-card-number-field-ef37b206']");
    
	public BookingPage(WebDriver driver) {
		this.driver=driver;
	}
//--------------------------------------------------------------------------------------------------------->
	public void selectHotel() {
	    List<WebElement> hotels = driver.findElements(hotelLocator);
	    if (hotels.size() > 0) {
	        hotels.get(1).click();
	        System.out.println("First hotel selected successfully " + hotels.size());
	    } else {
	        throw new RuntimeException("No hotels found in the search results!");
	    }
	    
	}


//--------------------------------------------------------------------------------------------------------->
	public void selectRoom() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement dropdown = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//select[contains(@id,'hprt_nos_select_')]")
	        )
	    );

	    Select select = new Select(dropdown);
	    boolean roomSelected = false;

	    for (WebElement option : select.getOptions()) {
	        String text = option.getText().trim();
	        if (!text.equals("0")) {
	            select.selectByVisibleText(text);
	            System.out.println("✅ Selected room option: " + text);
	            roomSelected = true;
	            break;
	        }
	    }

	    if (!roomSelected) {
	        throw new RuntimeException("❌ No valid room options available (all were 0).");
	    }
	}


//--------------------------------------------------------------------------------------------------------->
  
	public void clickReserveButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    try {
	        // Try first Reserve button
	        WebElement reserveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[@id='hp_book_now_button' or contains(text(),'Reserve')]")
	        ));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reserveBtn);
	        reserveBtn.click();
	        System.out.println("✅ Reserve button clicked");
	    } catch (Exception e1) {
	        System.out.println("⚠️ Primary reserve button not found, trying fallback...");

	        try {
	            // Alternative Reserve button (inside room table section)
	            WebElement reserveBtnAlt = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[contains(@class,'k2-hp--rt')]//button[contains(text(),'Reserve')]")
	            ));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reserveBtnAlt);
	            reserveBtnAlt.click();
	            System.out.println("✅ Alternative Reserve button clicked");
	        } catch (Exception e2) {
	            throw new RuntimeException("❌ Could not click any Reserve button!", e2);
	        }
	    }
	}
	
	


//---------------------------------------------------------------------------------------------------------->
	

public boolean isBookingSummaryDisplayed() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(bookingSummary));
        return summary.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

//----------------------------------------------------------------------------------------------------------->

public void enterFirstName(String Fname) {
	driver.findElement(firstName).sendKeys(Fname);
}
	
public void enterLastName(String Lname) {
	driver.findElement(lastName).sendKeys(Lname);
}
public void enterEmailId(String email) {
	driver.findElement(emailInput).sendKeys(email);
}

public void enterPhoneNo(String phoneNo) {
	driver.findElement(By.xpath("//input[@data-testid='phone-number-input' ]")).clear();
	driver.findElement(By.xpath("//input[@data-testid='phone-number-input' ]")).sendKeys(phoneNo);
//	driver.findElement(By.xpath("//label[@for=':r10:']//span[@class='fc70cba028 f823b234fe ca6ff50764']//*[name()='svg']")).click();
}

public void toPaymentPage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    WebElement finalDetailsBtn = wait.until(
        ExpectedConditions.elementToBeClickable(toPaymentButton)
    );
    finalDetailsBtn.click();

    wait.until(ExpectedConditions.urlContains("book.html"));
    String currentUrl = driver.getCurrentUrl();
    System.out.println("Navigated to URL: " + currentUrl);

    Assert.assertTrue(currentUrl.contains("book.html"), "Not on payment page!");

    try {
        WebElement cardNumberField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'pc-card-number-field')]"))
        );

        if (cardNumberField.isDisplayed()) {
            System.out.println("Payment page loaded successfully with card input visible.");
        }
    } catch (Exception e) {
        System.out.println("Payment page validated by URL only (card field not found yet).");
    }
}

//----------------------------------------------------------------------------------------------------------->
public void cardNoInput(String number) {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    WebElement cardField = wait.until(
	        ExpectedConditions.elementToBeClickable(cardNoInput)
	    );
	    cardField.sendKeys(number);
}


}
