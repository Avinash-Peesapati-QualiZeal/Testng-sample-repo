package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;

    // Locators
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value"); // Email address input
    public By emailTypeInput = By.name("name"); // Email type input (Personal, Business, etc.)
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button') and contains(., 'add')]");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and contains(., 'Save')]");
    public By confirmationMessage = By.xpath("<PLACEHOLDER_CONFIRMATION_MESSAGE>"); // TODO: Replace with actual locator for confirmation message
    public By phoneNumberInput = By.xpath("<PLACEHOLDER_PHONE_NUMBER_INPUT>"); // TODO: Replace with actual locator
    public By companyInput = By.xpath("<PLACEHOLDER_COMPANY_INPUT>"); // TODO: Replace with actual locator
    public By positionInput = By.xpath("<PLACEHOLDER_POSITION_INPUT>"); // TODO: Replace with actual locator

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Actions
    /**
     * Enter first name (for this test, a string at maximum allowed length)
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    /**
     * Enter last name
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    /**
     * Enter email address
     */
    public void enterEmailAddress(String email) {
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(email);
    }

    /**
     * Enter email type (e.g., Personal, Business)
     */
    public void enterEmailType(String emailType) {
        driver.findElement(emailTypeInput).clear();
        driver.findElement(emailTypeInput).sendKeys(emailType);
    }

    /**
     * Click Add Email button
     */
    public void clickAddEmailButton() {
        driver.findElement(addEmailButton).click();
    }

    /**
     * Enter phone number
     */
    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    /**
     * Enter company
     */
    public void enterCompany(String company) {
        driver.findElement(companyInput).clear();
        driver.findElement(companyInput).sendKeys(company);
    }

    /**
     * Enter position
     */
    public void enterPosition(String position) {
        driver.findElement(positionInput).clear();
        driver.findElement(positionInput).sendKeys(position);
    }

    /**
     * Click Save button to submit the form
     */
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    /**
     * Check if confirmation message is displayed
     */
    public boolean isConfirmationMessageDisplayed() {
        try {
            WebElement msg = driver.findElement(confirmationMessage);
            return msg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
