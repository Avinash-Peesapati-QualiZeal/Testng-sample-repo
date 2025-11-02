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

    // Locators (from provided test case context)
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button')]//i[contains(@class, 'add icon')]");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Save']");
    public By confirmationMessage = By.xpath("//div[contains(@class, 'confirmation message')]"); // TODO: Replace with actual locator

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enters the maximum allowed characters into the First Name field.
     * @param maxLengthName String with maximum allowed characters
     */
    public void enterFirstName(String maxLengthName) {
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstName.clear();
        firstName.sendKeys(maxLengthName);
    }

    /**
     * Enters the last name.
     * @param lastName Last name string
     */
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    /**
     * Enters the email address.
     * @param email Email address string
     */
    public void enterEmailAddress(String email) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    /**
     * Enters the email type (e.g., Personal, Business, etc.)
     * @param emailType Email type string
     */
    public void enterEmailType(String emailType) {
        WebElement emailTypeElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTypeInput));
        emailTypeElem.clear();
        emailTypeElem.sendKeys(emailType);
    }

    /**
     * Clicks the Add Email button.
     */
    public void clickAddEmailButton() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addEmailButton));
        addBtn.click();
    }

    /**
     * Clicks the Save button to save the contact.
     */
    public void clickSaveButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    /**
     * Checks if the confirmation message is displayed after saving.
     * @return true if confirmation message is displayed, false otherwise
     */
    public boolean isConfirmationMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
