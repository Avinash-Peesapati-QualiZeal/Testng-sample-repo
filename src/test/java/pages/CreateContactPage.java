package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateContactPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Placeholder locators for Create Contact page elements
    // TODO: Replace with actual locator for contact name field
    private By contactNameField = By.id("PLACEHOLDER_contact_name_field");
    // TODO: Replace with actual locator for contact email field
    private By contactEmailField = By.id("PLACEHOLDER_contact_email_field");
    // TODO: Replace with actual locator for contact phone field
    private By contactPhoneField = By.id("PLACEHOLDER_contact_phone_field");
    // TODO: Replace with actual locator for save button
    private By saveButton = By.id("PLACEHOLDER_save_button");
    // TODO: Replace with actual locator for success message
    private By successMessage = By.id("PLACEHOLDER_success_message");

    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Enter contact name
    public void enterContactName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactNameField));
        WebElement nameField = driver.findElement(contactNameField);
        nameField.clear();
        nameField.sendKeys(name);
    }

    // Enter contact email
    public void enterContactEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField));
        WebElement emailField = driver.findElement(contactEmailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Enter contact phone
    public void enterContactPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPhoneField));
        WebElement phoneField = driver.findElement(contactPhoneField);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    // Click Save button
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }

    // Get success message text
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    // Verify if success message is displayed
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
