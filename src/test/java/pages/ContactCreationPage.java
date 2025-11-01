package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;

    // Locators
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.cssSelector("button.ui.tiny.basic.icon.button");
    public By categoryDropdown = By.cssSelector("div.ui.selection.dropdown");
    public By saveButton = By.cssSelector("button.ui.linkedin.button");
    // Error messages (placeholders, as no specific locators provided)
    public By firstNameErrorMsg = By.xpath("//label[contains(.,'First Name')]/span[contains(@class,'inline-error-msg')]"); // TODO: Replace with actual locator if needed
    public By emailErrorMsg = By.xpath("//label[contains(.,'Email')]/span[contains(@class,'inline-error-msg')]"); // TODO: Replace with actual locator if needed

    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Leave First Name and Email fields empty")
    public void leaveRequiredFieldsEmpty() {
        driver.findElement(firstNameInput).clear();
        driver.findElement(emailAddressInput).clear();
    }

    @Step("Enter valid data in Phone Number, Company, and Position fields")
    public void enterValidOptionalFields(String phone, String company, String position) {
        // Placeholder locators for optional fields
        By phoneNumberInput = By.xpath("<PLACEHOLDER_PHONE_NUMBER_INPUT>"); // TODO: Replace with actual locator
        By companyInput = By.xpath("<PLACEHOLDER_COMPANY_INPUT>"); // TODO: Replace with actual locator
        By positionInput = By.xpath("<PLACEHOLDER_POSITION_INPUT>"); // TODO: Replace with actual locator
        driver.findElement(phoneNumberInput).sendKeys(phone);
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(positionInput).sendKeys(position);
    }

    @Step("Click the 'Save' button")
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    @Step("Get error message for First Name field")
    public String getFirstNameErrorMessage() {
        WebElement errorElement = driver.findElement(firstNameErrorMsg);
        return errorElement.isDisplayed() ? errorElement.getText() : "";
    }

    @Step("Get error message for Email field")
    public String getEmailErrorMessage() {
        WebElement errorElement = driver.findElement(emailErrorMsg);
        return errorElement.isDisplayed() ? errorElement.getText() : "";
    }
}