package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class CreateContactPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (from provided test case context)
    public By emailInput = By.name("email");
    public By passwordInput = By.name("password");
    public By loginButton = By.className("ui fluid large blue submit button");
    public By contactsLink = By.linkText("Contacts");
    public By createButton = By.className("ui linkedin button"); // Used for both 'Create' and 'Save' buttons
    public By firstNameInput = By.name("first_name");
    public By lastNameInput = By.name("last_name");
    public By emailAddressInput = By.name("value");
    public By emailTypeInput = By.name("name");
    public By addEmailButton = By.className("ui tiny basic icon button");
    public By categoryDropdown = By.className("ui selection dropdown");
    public By saveButton = By.className("ui linkedin button");
    public By successMessage = By.xpath("//div[contains(@class,'ui positive message')] | //div[contains(text(),'Contact created') or contains(text(),'successfully')]"); // TODO: Replace with actual locator if known

    // Constructor
    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Perform the complete flow to create a new contact as per TC-N001
     * Steps: Login, navigate to contacts, open create contact, enter all details, save, and verify message
     */
    @Step("Create a new contact with valid details and verify the success message")
    public void createNewContact(String loginEmail, String loginPassword, String firstName, String lastName, String email, String emailType, String category) {
        // Login
        WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailElem.clear();
        emailElem.sendKeys(loginEmail);

        WebElement passwordElem = driver.findElement(passwordInput);
        passwordElem.clear();
        passwordElem.sendKeys(loginPassword);

        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();

        // Navigate to Contacts
        WebElement contactsElem = wait.until(ExpectedConditions.visibilityOfElementLocated(contactsLink));
        contactsElem.click();

        // Click Create button
        WebElement createBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        createBtn.click();

        // Fill in contact details
        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameElem.clear();
        firstNameElem.sendKeys(firstName);

        WebElement lastNameElem = driver.findElement(lastNameInput);
        lastNameElem.clear();
        lastNameElem.sendKeys(lastName);

        WebElement emailAddrElem = driver.findElement(emailAddressInput);
        emailAddrElem.clear();
        emailAddrElem.sendKeys(email);

        WebElement emailTypeElem = driver.findElement(emailTypeInput);
        emailTypeElem.clear();
        emailTypeElem.sendKeys(emailType);

        WebElement addEmailBtn = driver.findElement(addEmailButton);
        addEmailBtn.click();

        // Select category from dropdown
        WebElement categoryElem = driver.findElement(categoryDropdown);
        categoryElem.click();
        // Select the desired category option (e.g., Lead, Customer, Contact, Affiliate)
        // TODO: Replace with a more robust locator for dropdown options if needed
        WebElement categoryOption = driver.findElement(By.xpath("//div[@class='menu transition']//div[@class='item']/span[text()='" + category + "']"));
        categoryOption.click();

        // Click Save button
        WebElement saveBtn = driver.findElement(saveButton);
        saveBtn.click();

        // Verify success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }
}
