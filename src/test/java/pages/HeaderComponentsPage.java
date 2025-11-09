package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and contains(., 'Create')]");
    private By trashIconButton = By.xpath("//button[contains(@class, 'button icon') and contains(., 'trash')]");
    private By settingsIcon = By.cssSelector("i.settings.icon"); // TODO: Replace with actual locator if needed
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all key header components are displayed after login.
     * This includes Contacts link, Create button, Trash icon, Settings icon, and Log Out link.
     */
    @Step("Validate header components are visible")
    public boolean areHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createVisible = driver.findElement(createButton).isDisplayed();
            boolean trashVisible = driver.findElement(trashIconButton).isDisplayed();
            boolean settingsVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean logoutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createVisible && trashVisible && settingsVisible && logoutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
