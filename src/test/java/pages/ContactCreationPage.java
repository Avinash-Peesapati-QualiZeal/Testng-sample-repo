package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ContactCreationPage {
    private WebDriver driver;

    // Locators
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.xpath("//input[@placeholder='Email address']");
    public By emailTypeInput = By.xpath("//input[@placeholder='Personal email, Business, Alt...']");
    public By addEmailButton = By.cssSelector("button.ui.tiny.basic.icon.button i.add.icon");
    public By categoryDropdown = By.cssSelector("div[name='category']");
    public By saveButton = By.cssSelector("button.ui.linkedin.button i.save.icon");
    public By confirmationMessage = By.xpath("//div[contains(@class,'confirmation') or contains(text(),'Contact saved')]"); // TODO: Replace with actual locator

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Complete the contact creation flow with maximum characters in the Name field.
     * @param maxFirstName String with maximum allowed characters for First Name
     * @param lastName Valid last name
     * @param email Valid email address
     * @param emailType Email type (e.g., Personal, Business)
     * @param category Category to select (e.g., Lead, Customer)
     */
    @Step("Create contact with maximum character limit for Name field")
    public void createContactWithMaxName(String maxFirstName, String lastName, String email, String emailType, String category) {
        // Enter First Name (max chars)
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(maxFirstName);

        // Enter Last Name
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);

        // Enter Email Address
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(email);

        // Enter Email Type
        driver.findElement(emailTypeInput).clear();
        driver.findElement(emailTypeInput).sendKeys(emailType);

        // Click Add Email Button
        driver.findElement(addEmailButton).click();

        // Select Category from Dropdown
        driver.findElement(categoryDropdown).click();
        // Select the category option (e.g., Lead, Customer, etc.)
        WebElement categoryOption = driver.findElement(By.xpath("//div[@name='category']//span[text()='" + category + "']"));
        categoryOption.click();

        // Click Save Button
        driver.findElement(saveButton).click();
    }

    /**
     * Check if confirmation message is displayed after saving contact.
     * @return true if confirmation message is displayed, false otherwise
     */
    public boolean isConfirmationMessageDisplayed() {
        try {
            return driver.findElement(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Fills the contact creation form with maximum allowed characters in the Name field and saves the contact.
     * This method is specifically for test case TC-N004: Maximum Character Limit for Name Field.
     *
     * @param maxFirstName String with maximum allowed characters for First Name
     * @param lastName Valid last name
     * @param email Valid email address
     * @param company Company name
     * @param position Position or job title
     */
    public void createContactWithMaxNameField(String maxFirstName, String lastName, String email, String company, String position) {
        // Enter First Name (max chars)
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(maxFirstName);

        // Enter Last Name
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);

        // Enter Email Address
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(email);

        // Enter Company
        By companyDropdown = By.cssSelector("div[role='combobox']");
        driver.findElement(companyDropdown).click();
        // Enter company name in dropdown search input
        By companySearchInput = By.cssSelector("div[role='combobox'] input.search");
        driver.findElement(companySearchInput).sendKeys(company);
        // Select the company from the dropdown (assumes exact match)
        By companyOption = By.xpath("//div[@role='combobox']//span[text()='" + company + "']");
        if (driver.findElements(companyOption).size() > 0) {
            driver.findElement(companyOption).click();
        }

        // Enter Position (assuming a placeholder locator as it's not defined)
        By positionInput = By.xpath("<PLACEHOLDER_Position_Input>"); // TODO: Replace with actual locator
        if (driver.findElements(positionInput).size() > 0) {
            driver.findElement(positionInput).clear();
            driver.findElement(positionInput).sendKeys(position);
        }

        // Click Save Button
        By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and contains(text(), 'Save')]");
        driver.findElement(saveButton).click();
    }

    /**
     * Checks if the confirmation message is displayed after saving the contact.
     * @return true if confirmation message is displayed, false otherwise
     */
    public boolean isConfirmationMessageDisplayedForMaxName() {
        // Placeholder for confirmation message locator (reuse if already defined)
        By confirmationMessage = By.xpath("//div[contains(@class,'confirmation') or contains(text(),'Contact saved')]");
        try {
            return driver.findElement(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}