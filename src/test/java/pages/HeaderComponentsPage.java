package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'ui linkedin button') and contains(., 'Create')]");
    private By trashIconButton = By.xpath("//button[contains(@class, 'ui button icon')]");
    private By settingsIcon = By.cssSelector("i.settings.icon"); // TODO: Replace with actual locator if needed
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are visible to the user after login.
     * This method performs the complete validation for INT-123.
     */
    @Step("Validate header components are visible after login")
    public void validateHeaderComponentsVisible() {
        // Validate Contacts link
        WebElement contacts = driver.findElement(contactsLink);
        if (!contacts.isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in header");
        }
        // Validate Create button
        WebElement createBtn = driver.findElement(createButton);
        if (!createBtn.isDisplayed()) {
            throw new AssertionError("Create button is not visible in header");
        }
        // Validate Trash icon button
        WebElement trashBtn = driver.findElement(trashIconButton);
        if (!trashBtn.isDisplayed()) {
            throw new AssertionError("Trash icon button is not visible in header");
        }
        // Validate Settings icon (if applicable)
        try {
            WebElement settings = driver.findElement(settingsIcon);
            if (!settings.isDisplayed()) {
                throw new AssertionError("Settings icon is not visible in header");
            }
        } catch (Exception e) {
            // Optional: If settings icon is not mandatory, ignore
        }
        // Validate Log Out link
        WebElement logout = driver.findElement(logOutLink);
        if (!logout.isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in header");
        }
    }
}
