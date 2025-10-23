package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (placeholders, update with actual values)
    private By addContactButton = By.id("PLACEHOLDER_add_contact_button"); // TODO: Replace with actual locator
    private By emailInputField = By.id("PLACEHOLDER_email_input_field"); // TODO: Replace with actual locator
    private By saveContactButton = By.id("PLACEHOLDER_save_contact_button"); // TODO: Replace with actual locator
    private By duplicateEmailErrorMessage = By.id("PLACEHOLDER_duplicate_email_error_message"); // TODO: Replace with actual locator
    private By contactForm = By.id("PLACEHOLDER_contact_form"); // TODO: Replace with actual locator

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click the 'Add Contact' button
    public void clickAddContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addContactButton));
        driver.findElement(addContactButton).click();
    }

    // Enter email in the email input field
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField));
        WebElement emailField = driver.findElement(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Click the 'Save' button to attempt to create the contact
    public void clickSaveContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton));
        driver.findElement(saveContactButton).click();
    }

    // Check if duplicate email error message is displayed
    public boolean isDuplicateEmailErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateEmailErrorMessage));
            return driver.findElement(duplicateEmailErrorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Get the duplicate email error message text
    public String getDuplicateEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateEmailErrorMessage));
        return driver.findElement(duplicateEmailErrorMessage).getText();
    }

    // Check if contact form is still displayed (i.e., contact was not saved)
    public boolean isContactFormDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactForm));
            return driver.findElement(contactForm).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
