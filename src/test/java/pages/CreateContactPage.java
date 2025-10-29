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

    // Locators (placeholders, to be replaced with actual locators)
    private By contactNameField = By.id("PLACEHOLDER_contact_name_field"); // TODO: Replace with actual locator
    private By contactEmailField = By.id("PLACEHOLDER_contact_email_field"); // TODO: Replace with actual locator
    private By contactPhoneField = By.id("PLACEHOLDER_contact_phone_field"); // TODO: Replace with actual locator
    private By saveContactButton = By.id("PLACEHOLDER_save_contact_button"); // TODO: Replace with actual locator
    private By successMessage = By.id("PLACEHOLDER_success_message"); // TODO: Replace with actual locator

    // Constructor
    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void enterContactName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactNameField));
        WebElement nameField = driver.findElement(contactNameField);
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterContactEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField));
        WebElement emailField = driver.findElement(contactEmailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterContactPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPhoneField));
        WebElement phoneField = driver.findElement(contactPhoneField);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void clickSaveContact() {
        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton));
        driver.findElement(saveContactButton).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    // Optional: Validation method for the expected result
    public boolean isContactCreationSuccessful(String expectedMessage) {
        String actualMessage = getSuccessMessage();
        return actualMessage != null && actualMessage.contains(expectedMessage);
    }
}
