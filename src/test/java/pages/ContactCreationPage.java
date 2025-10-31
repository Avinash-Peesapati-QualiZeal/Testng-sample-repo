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
    public By emailDescriptionInput = By.name("name");
    public By addEmailButton = By.xpath("//button[@class='ui tiny basic icon button']");
    public By saveButton = By.xpath("//button[@class='ui linkedin button']/i[@class='save icon']");
    public By confirmationMessage = By.xpath("<PLACEHOLDER_confirmation_message>"); // TODO: Replace with actual locator

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Fills the First Name field with the given value (for maximum character limit test)
     */
    public void enterFirstName(String firstName) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);
    }

    /**
     * Fills the Last Name field with the given value
     */
    public void enterLastName(String lastName) {
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);
    }

    /**
     * Fills the Email Address and Description fields and adds the email
     */
    public void enterEmail(String email, String description) {
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailElem.clear();
        emailElem.sendKeys(email);
        WebElement descElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailDescriptionInput));
        descElem.clear();
        descElem.sendKeys(description);
        driver.findElement(addEmailButton).click();
    }

    /**
     * Clicks the Save button to create the contact
     */
    public void clickSaveButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    /**
     * Checks if confirmation message is displayed after saving contact
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
