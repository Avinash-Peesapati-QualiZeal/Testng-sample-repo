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

    // Locators (placeholders, replace with actual locators)
    private By firstNameField = By.id("PLACEHOLDER_first_name_field"); // TODO: Replace with actual locator
    private By lastNameField = By.id("PLACEHOLDER_last_name_field"); // TODO: Replace with actual locator
    private By emailField = By.id("PLACEHOLDER_email_field"); // TODO: Replace with actual locator
    private By saveButton = By.id("PLACEHOLDER_save_button"); // TODO: Replace with actual locator
    private By successMessage = By.id("PLACEHOLDER_success_message"); // TODO: Replace with actual locator

    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions on the Create Contact page
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        WebElement element = driver.findElement(firstNameField);
        element.clear();
        element.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        WebElement element = driver.findElement(lastNameField);
        element.clear();
        element.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        WebElement element = driver.findElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    // Utility method to create a new contact with all fields
    public void createNewContact(String firstName, String lastName, String email) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        clickSaveButton();
    }
}
