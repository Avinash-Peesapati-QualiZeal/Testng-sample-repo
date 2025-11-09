package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderPage {
    private WebDriver driver;

    // Locators for header components
    public By contactsLink = By.linkText("Contacts");
    public By createButton = By.xpath("//button[contains(@class, 'linkedin') and contains(., 'Create')]");
    public By logOutLink = By.linkText("Log Out");
    public By settingsIcon = By.cssSelector("i.settings.icon");
    public By userIcon = By.cssSelector("i.large.user.red.icon");

    // Constructor
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are visible to the user after login.
     * This includes Contacts link, Create button, Log Out link, Settings icon, and User icon.
     * Throws AssertionError if any component is not displayed.
     */
    @Step("Validate header components are visible")
    public void validateHeaderComponentsVisible() {
        if (!driver.findElement(contactsLink).isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in header");
        }
        if (!driver.findElement(createButton).isDisplayed()) {
            throw new AssertionError("Create button is not visible in header");
        }
        if (!driver.findElement(logOutLink).isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in header");
        }
        if (!driver.findElement(settingsIcon).isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in header");
        }
        if (!driver.findElement(userIcon).isDisplayed()) {
            throw new AssertionError("User icon is not visible in header");
        }
    }
}
