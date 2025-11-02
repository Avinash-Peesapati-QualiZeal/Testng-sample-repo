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
    public By emailInput = By.name("value"); // Email address input
    public By emailTypeInput = By.name("name"); // Email type input (Personal, Business, etc.)
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button')]//i[contains(@class, 'add icon')]");
    public By categoryDropdown = By.xpath("//div[contains(@class, 'ui selection dropdown')]");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button')]//i[contains(@class, 'save icon')]");
    // Error messages (placeholders, as no specific locators provided)
    public By firstNameErrorMsg = By.xpath("//label[contains(.,'First Name')]/span[contains(@class,'inline-error-msg')]"); // TODO: Replace with actual locator if different
    public By emailErrorMsg = By.xpath("//label[contains(.,'Email')]/following-sibling::span[contains(@class,'inline-error-msg')]"); // TODO: Replace with actual locator if different

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Actions
    /**
     * Leaves required fields empty and fills optional fields, then clicks Save.
     * Used for 'Empty Required Fields Validation' test case (TC-N005).
     * @param phoneNumber (not implemented, as locator not provided)
     * @param company (not implemented, as locator not provided)
     * @param position (not implemented, as locator not provided)
     */
    public void attemptToSaveContactWithEmptyRequiredFields() {
        // Leave First Name and Email fields empty
        driver.findElement(firstNameInput).clear();
        driver.findElement(emailInput).clear();
        // Fill optional fields if locators are available (not implemented due to missing locators)
        // Example: driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
        // Example: driver.findElement(companyInput).sendKeys(company);
        // Example: driver.findElement(positionInput).sendKeys(position);
        // Click Save
        driver.findElement(saveButton).click();
    }

    /**
     * Checks if the error message for First Name is displayed.
     * @return true if error message is displayed, false otherwise
     */
    public boolean isFirstNameErrorDisplayed() {
        try {
            WebElement error = driver.findElement(firstNameErrorMsg);
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the error message for Email is displayed.
     * @return true if error message is displayed, false otherwise
     */
    public boolean isEmailErrorDisplayed() {
        try {
            WebElement error = driver.findElement(emailErrorMsg);
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
