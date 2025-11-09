package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderPage {
    private WebDriver driver;

    // Locators for header components (placeholders where actual locators are not provided)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Create']");
    private By logOutLink = By.linkText("Log Out");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");

    // Constructor
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all header components are visible after login.
     * This includes: Contacts link, Create button, Log Out link, User icon, and Settings icon.
     */
    @Step("Validate header components are visible")
    public boolean validateHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            boolean userIconVisible = driver.findElement(userIcon).isDisplayed();
            boolean settingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            return contactsVisible && createBtnVisible && logOutVisible && userIconVisible && settingsIconVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
