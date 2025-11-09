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
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.xpath("//a[contains(@class, 'item')]/span[contains(., 'Log Out')]");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all header components are visible to the user after login.
     * This includes: Contacts link, Create button, User icon, Settings icon, and Log Out link.
     */
    @Step("Validate header components are displayed")
    public boolean validateHeaderComponentsDisplayed() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean userIconVisible = driver.findElement(userIcon).isDisplayed();
            boolean settingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createBtnVisible && userIconVisible && settingsIconVisible && logOutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
