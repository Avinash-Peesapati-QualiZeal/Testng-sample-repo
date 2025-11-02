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
    public By addEmailButton = By.xpath("//button[contains(@class, 'basic icon button')]");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Save']");
    public By confirmationMessage = By.xpath("//div[contains(@class, 'confirmation message')]"); // TODO: Replace with actual locator if available

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
     * Enters the last name into the Last Name field.
     * @param lastName String last name
     */
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    /**
     * Enters the email address into the Email Address field.
     * @param email String email address
     */
    public void enterEmailAddress(String email) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailElem.clear();
        emailElem.sendKeys(email);
    }

    /**
     * Enters the email type into the Email Type field.
     * @param type String email type (e.g., Personal, Business)
     */
    public void enterEmailType(String type) {
        WebElement typeElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTypeInput));
        typeElem.clear();
        typeElem.sendKeys(type);
    }

    /**
     * Clicks the Add Email button.
     */
    public void clickAddEmailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmailButton)).click();
    }

    /**
     * Clicks the Save button to save the contact.
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    /**
     * Waits for and returns the confirmation message after saving.
     * @return String confirmation message text
     */
    public String getConfirmationMessage() {
        // TODO: Update locator if actual confirmation message locator is different
        WebElement messageElem = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return messageElem.getText();
    }
}
