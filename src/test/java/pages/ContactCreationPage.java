package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (from provided test case context and extracted HTML)
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.xpath("//input[@placeholder='Email address']");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Save']");
    public By confirmationMessage = By.xpath("//div[contains(@class, 'confirmation message')]"); // TODO: Replace with actual locator if available

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Enter first name (handles special characters)
    public void enterFirstName(String firstName) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);
    }

    // Enter last name
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    // Enter email address
    public void enterEmailAddress(String email) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    // Click Save button
    public void clickSaveButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    // Verify confirmation message is displayed
    public boolean isConfirmationMessageDisplayed() {
        try {
            WebElement confirmationElem = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
            return confirmationElem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Composite action for the test case scenario
    /**
     * Handles special characters in the Name field and saves the contact.
     * @param firstName The first name (with special characters)
     * @param lastName The last name
     * @param email The email address
     */
    public void createContactWithSpecialCharacters(String firstName, String lastName, String email) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmailAddress(email);
        clickSaveButton();
    }
}
