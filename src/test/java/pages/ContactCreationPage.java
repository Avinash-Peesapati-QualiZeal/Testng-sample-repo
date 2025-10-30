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

    // Locators (using provided locators and descriptive placeholders where needed)
    public By emailInput = By.name("email");
    public By passwordInput = By.name("password");
    public By loginButton = By.xpath("//div[contains(@class, 'submit') and text()='Login']");
    public By contactsLink = By.linkText("Contacts");
    public By createButton = By.xpath("//button[contains(@class, 'linkedin') and text()='Create']");
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon') and contains(@class, 'add')]");
    public By categoryDropdown = By.xpath("//div[@role='listbox']");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin') and text()='Save']");
    public By successMessage = By.xpath("//div[contains(@class, 'ui message')]"); // TODO: Replace with actual locator for success message

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions for login (if needed in test flow)
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    // Navigation to Contacts
    public void clickContactsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsLink));
        driver.findElement(contactsLink).click();
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        driver.findElement(createButton).click();
    }

    // Contact creation form actions
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmailAddress(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(email);
    }

    public void enterEmailType(String type) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailTypeInput));
        driver.findElement(emailTypeInput).clear();
        driver.findElement(emailTypeInput).sendKeys(type);
    }

    public void clickAddEmailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmailButton));
        driver.findElement(addEmailButton).click();
    }

    public void selectCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        WebElement dropdown = driver.findElement(categoryDropdown);
        dropdown.click();
        // Select the category by visible text
        By categoryOption = By.xpath("//div[@role='option']//span[text()='" + category + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryOption));
        driver.findElement(categoryOption).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }

    // Validation
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Add more validation methods as needed for the test case
}
