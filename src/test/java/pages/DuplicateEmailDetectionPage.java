package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DuplicateEmailDetectionPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (from provided test case and available locators)
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailDescriptionInput = By.name("name");
    public By addEmailButton = By.xpath("//button[@class='ui tiny basic icon button']");
    public By saveButton = By.xpath("//button[@class='ui linkedin button']");
    public By errorMessage = By.xpath("//div[contains(@class,'error') or contains(text(),'already in use')]"); // TODO: Replace with actual locator if available
    // Add more locators as needed for Phone Number, Company, Position fields
    public By phoneNumberInput = By.xpath("//input[@name='phone']"); // TODO: Replace with actual locator
    public By companyInput = By.xpath("//input[@name='company']"); // TODO: Replace with actual locator
    public By positionInput = By.xpath("//input[@name='position']"); // TODO: Replace with actual locator

    public DuplicateEmailDetectionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Perform all actions to attempt to create a contact with a duplicate email and verify error message.
     * Steps:
     * 1. Enter valid data in Name, Phone Number, Company, and Position fields.
     * 2. Enter 'test@example.com' in the Email field.
     * 3. Click the 'Save' button.
     * 4. Verify that error message is displayed indicating the email is already in use.
     */
    public void createContactWithDuplicateEmail(String firstName, String lastName, String phone, String company, String position, String email, String emailDescription) {
        // Enter First Name
        WebElement firstNameField = driver.findElement(firstNameInput);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        // Enter Last Name
        WebElement lastNameField = driver.findElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Enter Phone Number
        WebElement phoneField = driver.findElement(phoneNumberInput); // TODO: Replace with actual locator
        phoneField.clear();
        phoneField.sendKeys(phone);

        // Enter Company
        WebElement companyField = driver.findElement(companyInput); // TODO: Replace with actual locator
        companyField.clear();
        companyField.sendKeys(company);

        // Enter Position
        WebElement positionField = driver.findElement(positionInput); // TODO: Replace with actual locator
        positionField.clear();
        positionField.sendKeys(position);

        // Enter Email Address
        WebElement emailField = driver.findElement(emailAddressInput);
        emailField.clear();
        emailField.sendKeys(email);

        // Enter Email Description (optional)
        WebElement emailDescField = driver.findElement(emailDescriptionInput);
        emailDescField.clear();
        emailDescField.sendKeys(emailDescription);

        // Click Add Email Button
        driver.findElement(addEmailButton).click();

        // Click Save Button
        WebElement saveBtn = driver.findElement(saveButton);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();
    }

    /**
     * Verifies if the duplicate email error message is displayed.
     * @return true if error message is displayed, false otherwise
     */
    public boolean isDuplicateEmailErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
