package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.xpath("//a[@href='/contacts']");
    private By createButton = By.xpath("//a[@href='/contacts/new']/button");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.xpath("//a//span[text()='Log Out']");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all header components are visible after successful login.
     * This includes Contacts link, Create button, User icon, Settings icon, and Log Out link.
     * Throws AssertionError if any component is not displayed.
     */
    @Step("Validate header components are displayed")
    public void validateHeaderComponentsVisible() {
        if (!driver.findElement(contactsLink).isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in header");
        }
        if (!driver.findElement(createButton).isDisplayed()) {
            throw new AssertionError("Create button is not visible in header");
        }
        if (!driver.findElement(userIcon).isDisplayed()) {
            throw new AssertionError("User icon is not visible in header");
        }
        if (!driver.findElement(settingsIcon).isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in header");
        }
        if (!driver.findElement(logOutLink).isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in header");
        }
    }
}
