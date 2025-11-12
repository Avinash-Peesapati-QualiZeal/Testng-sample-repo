package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (placeholders where actual locators are not provided)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//a[@href='/contacts/new']/button");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Complete flow: Validates that all header components are visible after login.
     * This method performs all necessary checks for INT-123.
     * @return true if all header components are displayed, false otherwise
     */
    @Step("Validate header components are displayed after login")
    public boolean validateHeaderComponents() {
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
