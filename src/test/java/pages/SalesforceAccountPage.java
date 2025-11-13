package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class SalesforceAccountPage {
    private WebDriver driver;

    // Locators
    public By usernameInput = By.id("PLACEHOLDER_username_input"); // TODO: Replace with actual locator
    public By passwordInput = By.id("PLACEHOLDER_password_input"); // TODO: Replace with actual locator
    public By loginButton = By.id("PLACEHOLDER_login_button"); // TODO: Replace with actual locator
    public By accountSearchBox = By.id("PLACEHOLDER_account_search_box"); // TODO: Replace with actual locator
    public By clientListItem = By.xpath("//div[contains(@class,'client-list-item') and text()='PLACEHOLDER_CLIENT_NAME']"); // TODO: Replace with actual locator
    public By accountPageHeader = By.id("PLACEHOLDER_account_page_header"); // TODO: Replace with actual locator

    // Constructor
    public SalesforceAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Log in to Salesforce with provided credentials.
     * @param username Salesforce username
     * @param password Salesforce password
     */
    @Step("Log in to Salesforce with username: {0}")
    public void loginToSalesforce(String username, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    /**
     * Search for a client account by name.
     * @param clientName Name of the client to search for
     */
    @Step("Search for client account: {0}")
    public void searchForClientAccount(String clientName) {
        WebElement searchBox = driver.findElement(accountSearchBox);
        searchBox.clear();
        searchBox.sendKeys(clientName);
        // Optionally, trigger search (e.g., press Enter or click search button)
        // TODO: Add logic to trigger search if required
    }

    /**
     * Select an existing client from the account list.
     * @param clientName Name of the client to select
     */
    @Step("Select existing client from account list: {0}")
    public void selectExistingClient(String clientName) {
        By clientLocator = By.xpath("//div[contains(@class,'client-list-item') and text()='" + clientName + "']"); // TODO: Replace with actual locator
        driver.findElement(clientLocator).click();
    }

    /**
     * Check if navigated to the client's account page.
     * @return true if account page header is displayed, false otherwise
     */
    public boolean isOnClientAccountPage() {
        try {
            return driver.findElement(accountPageHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
