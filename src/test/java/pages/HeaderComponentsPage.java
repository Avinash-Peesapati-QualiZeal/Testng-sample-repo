package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (based on provided locators and HTML snippets)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//a[@href='/contacts/new']/button");
    private By logOutLink = By.linkText("Log Out");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By userIcon = By.cssSelector("i.large.user.red.icon");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all header components are visible and interactable.
     * This includes Contacts link, Create button, Log Out link, Settings icon, and User icon.
     * Throws AssertionError if any component is missing.
     */
    @Step("Validate header components are visible and interactable")
    public void validateHeaderComponents() {
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

        // Validate Log Out link
        WebElement logout = driver.findElement(logOutLink);
        if (!logout.isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in header");
        }

        // Validate Settings icon
        WebElement settings = driver.findElement(settingsIcon);
        if (!settings.isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in header");
        }

        // Validate User icon
        WebElement user = driver.findElement(userIcon);
        if (!user.isDisplayed()) {
            throw new AssertionError("User icon is not visible in header");
        }
    }
}
