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

    // Locators
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.cssSelector("button.ui.tiny.basic.icon.button");
    public By saveButton = By.cssSelector("button.ui.linkedin.button");
    public By confirmationMessage = By.xpath("//div[contains(@class, 'confirmation') or contains(text(), 'Contact saved')]"); // TODO: Replace with actual locator if available

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enters the first name in the First Name field.
     * @param firstName The first name to enter (can include special characters).
     */
    public void enterFirstName(String firstName) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);
    }

    /**
     * Enters the last name in the Last Name field.
     * @param lastName The last name to enter.
     */
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    /**
     * Enters the email address in the Email Address field.
     * @param email The email address to enter.
     */
    public void enterEmailAddress(String email) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    /**
     * Enters the email type in the Email Type field (e.g., Personal, Business).
     * @param emailType The email type to enter.
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
        WebElement addEmailBtn = wait.until(ExpectedConditions.elementToBeClickable(addEmailButton));
        addEmailBtn.click();
    }

    /**
     * Clicks the Save button to save the contact.
     */
    public void clickSaveButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    /**
     * Waits for the confirmation message to appear after saving.
     * @return true if confirmation message is displayed, false otherwise.
     */
    public boolean isConfirmationMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Complete flow for entering contact details with special characters in the name and saving the contact.
     * @param firstName The first name (with special characters)
     * @param lastName The last name
     * @param email The email address
     * @param emailType The email type
     */
    public void createContactWithSpecialCharacters(String firstName, String lastName, String email, String emailType) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmailAddress(email);
        enterEmailType(emailType);
        clickAddEmailButton();
        clickSaveButton();
    }
}
