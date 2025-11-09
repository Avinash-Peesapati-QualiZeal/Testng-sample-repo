package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (as per provided locators and UI context)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'ui linkedin button') and ./i[@class='edit icon']]");
    private By trashIconButton = By.xpath("//button[contains(@class, 'ui button icon') and ./i[@class='trash icon']]");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are visible after login.
     * This includes: Contacts link, Create button, Trash icon button, Settings icon, and Log Out link.
     */
    @Step("Validate header components are visible after login")
    public boolean validateHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean trashBtnVisible = driver.findElement(trashIconButton).isDisplayed();
            boolean settingsVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean logoutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createBtnVisible && trashBtnVisible && settingsVisible && logoutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
