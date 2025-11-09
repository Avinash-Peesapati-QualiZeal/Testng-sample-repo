package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and .//i[contains(@class, 'edit icon')]]");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are visible after successful login.
     * This covers: Contacts link, Create button, Settings icon, User icon, and Log Out link.
     */
    @Step("Validate header components are visible")
    public boolean validateHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean settingsVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean userIconVisible = driver.findElement(userIcon).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createBtnVisible && settingsVisible && userIconVisible && logOutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
