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
    public By addEmailButton = By.xpath("//button[contains(@class, 'icon button') and contains(., 'add')]");
    public By categoryDropdown = By.xpath("//div[@role='listbox' and @name='category']");
    public By saveButton = By.xpath("//button[contains(@class, 'linkedin') and contains(., 'Save')]");
    public By successMessage = By.xpath("//div[contains(@class, 'ui message') or contains(@class, 'success')]"); // TODO: Replace with actual locator for success message

    // Constructor
    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Create a new contact by entering all required fields and saving.
     * @param firstName First Name of the contact
     * @param lastName Last Name of the contact
     * @param emailAddress Email Address of the contact
     * @param emailType Email type (e.g. Personal, Business)
     * @param category Category to select (e.g. Lead, Customer)
     */
    @Step("Create a new contact with firstName: {0}, lastName: {1}, emailAddress: {2}, emailType: {3}, category: {4}")
    public void createNewContact(String firstName, String lastName, String emailAddress, String emailType, String category) {
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);

        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);

        WebElement emailAddressElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressInput));
        emailAddressElem.clear();
        emailAddressElem.sendKeys(emailAddress);

        WebElement emailTypeElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTypeInput));
        emailTypeElem.clear();
        emailTypeElem.sendKeys(emailType);

        WebElement addEmailBtn = wait.until(ExpectedConditions.elementToBeClickable(addEmailButton));
        addEmailBtn.click();

        WebElement categoryDropdownElem = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        categoryDropdownElem.click();
        // Select the category option
        By categoryOption = By.xpath("//div[@role='option' and @name='category']//span[text()='" + category + "']");
        WebElement categoryOptionElem = wait.until(ExpectedConditions.elementToBeClickable(categoryOption));
        categoryOptionElem.click();

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    /**
     * Verify if the success message is displayed after saving contact
     * @return true if message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
