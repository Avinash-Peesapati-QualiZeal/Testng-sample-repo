package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (using provided locators and descriptive placeholders)
    public By emailInput = By.name("email");
    public By passwordInput = By.name("password");
    public By loginButton = By.xpath("//div[contains(@class, 'submit') and contains(text(), 'Login')]");
    public By contactsLink = By.linkText("Contacts");
    public By createButton = By.xpath("//button[contains(text(), 'Create')]");
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.xpath("//input[@placeholder='Email address']");
    public By emailTypeInput = By.xpath("//input[@placeholder='Personal email, Business, Alt...']");
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button') and .//i[contains(@class, 'add icon')]]");
    public By categoryDropdown = By.xpath("//div[contains(@class, 'ui selection dropdown')]");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and .//i[contains(@class, 'save icon')]]");
    public By successMessage = By.xpath("//div[contains(@class, 'message')]"); // TODO: Replace with actual locator for success message
    public By companyInput = By.id("PLACEHOLDER_company_input"); // TODO: Replace with actual locator
    public By phoneNumberInput = By.id("PLACEHOLDER_phone_number_input"); // TODO: Replace with actual locator

    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    // Login actions
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

    // Navigation actions
    public void clickContactsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsLink));
        driver.findElement(contactsLink).click();
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        driver.findElement(createButton).click();
    }

    // Contact creation actions
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

    public void enterCompany(String company) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(companyInput));
        driver.findElement(companyInput).clear();
        driver.findElement(companyInput).sendKeys(company);
    }

    public void enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput));
        driver.findElement(phoneNumberInput).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void enterEmailAddress(String emailAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(emailAddress);
    }

    public void enterEmailType(String emailType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailTypeInput));
        driver.findElement(emailTypeInput).clear();
        driver.findElement(emailTypeInput).sendKeys(emailType);
    }

    public void clickAddEmailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmailButton));
        driver.findElement(addEmailButton).click();
    }

    public void selectCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        driver.findElement(categoryDropdown).click();
        By categoryOption = By.xpath("//div[@role='option' and .//span[text()='" + category + "']]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryOption));
        driver.findElement(categoryOption).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
    }

    // Validation
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }
}
