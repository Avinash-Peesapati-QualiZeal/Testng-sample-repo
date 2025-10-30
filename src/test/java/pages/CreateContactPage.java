package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.qameta.allure.Step;

public class CreateContactPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (from provided test case context)
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button')]/i[@class='add icon']");
    public By phoneNumberInput = By.xpath("//input[@name='phone']"); // TODO: Replace with actual locator if available
    public By companyInput = By.xpath("//input[@name='company']"); // TODO: Replace with actual locator if available
    public By categoryDropdown = By.xpath("//div[@class='ui selection dropdown']");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Save']");
    public By successMessage = By.xpath("//div[contains(@class,'message') and contains(text(),'successfully')]"); // TODO: Replace with actual locator if available

    // Constructor
    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Create a new contact by filling all required fields and saving.
     * @param firstName First name of the contact
     * @param lastName Last name of the contact
     * @param email Email address
     * @param emailType Email type (e.g., Personal, Business)
     * @param phoneNumber Phone number
     * @param company Company name
     * @param category Category to select (e.g., Lead, Customer)
     */
    @Step("Create a new contact with valid details and save")
    public void createNewContact(String firstName, String lastName, String email, String emailType, String phoneNumber, String company, String category) {
        // Enter first name
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        // Enter last name
        WebElement lastNameField = driver.findElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Enter phone number
        WebElement phoneField = driver.findElement(phoneNumberInput); // Placeholder locator
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);

        // Enter company
        WebElement companyField = driver.findElement(companyInput); // Placeholder locator
        companyField.clear();
        companyField.sendKeys(company);

        // Enter email address
        WebElement emailField = driver.findElement(emailAddressInput);
        emailField.clear();
        emailField.sendKeys(email);

        // Enter email type
        WebElement emailTypeField = driver.findElement(emailTypeInput);
        emailTypeField.clear();
        emailTypeField.sendKeys(emailType);

        // Optionally add email (if required by UI)
        // driver.findElement(addEmailButton).click();

        // Select category from dropdown
        WebElement categoryDrop = driver.findElement(categoryDropdown);
        categoryDrop.click();
        // Select the category option
        WebElement categoryOption = driver.findElement(By.xpath("//div[@class='menu transition']//div[@role='option']//span[text()='" + category + "']"));
        categoryOption.click();

        // Click Save button
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    /**
     * Verify that the success message is displayed after saving contact.
     * @return true if message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
