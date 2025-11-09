package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentPage {
    private WebDriver driver;

    // Locators for header components (using provided locators and descriptive placeholders where necessary)
    public By contactsLink = By.xpath("//a[@href='/contacts']");
    public By createButton = By.xpath("//a[@href='/contacts/new']/button");
    public By logOutLink = By.xpath("//a[contains(@class, 'item') and contains(., 'Log Out')]");
    private By userIcon = By.cssSelector("i.large.user.red.icon"); // Placeholder for user profile icon
    private By settingsIcon = By.cssSelector("i.settings.icon"); // Placeholder for settings icon

    // Constructor
    public HeaderComponentPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all main header components are visible to the user after login.
     * This includes Contacts link, Create button, User icon, Settings icon, and Log Out link.
     */
    @Step("Validate header components are visible")
    public void validateHeaderComponentsVisible() {
        if (!driver.findElement(contactsLink).isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in the header");
        }
        if (!driver.findElement(createButton).isDisplayed()) {
            throw new AssertionError("Create button is not visible in the header");
        }
        if (!driver.findElement(userIcon).isDisplayed()) {
            throw new AssertionError("User icon is not visible in the header");
        }
        if (!driver.findElement(settingsIcon).isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in the header");
        }
        if (!driver.findElement(logOutLink).isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in the header");
        }
    }
}
