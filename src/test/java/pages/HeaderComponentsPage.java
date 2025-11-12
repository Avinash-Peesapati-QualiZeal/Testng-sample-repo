package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (placeholders where actual locators are not provided)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and .//i[contains(@class, 'edit icon')]]");
    private By logOutLink = By.linkText("Log Out");
    private By settingsIcon = By.xpath("//i[contains(@class, 'settings icon')]"); // TODO: Replace with actual locator if needed
    private By userIcon = By.xpath("//i[contains(@class, 'user red icon')]"); // TODO: Replace with actual locator if needed

    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Validate header components are visible and accessible")
    public void validateHeaderComponents() {
        // Validate Contacts link is displayed
        WebElement contacts = driver.findElement(contactsLink);
        if (!contacts.isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in the header");
        }

        // Validate Create button is displayed
        WebElement createBtn = driver.findElement(createButton);
        if (!createBtn.isDisplayed()) {
            throw new AssertionError("Create button is not visible in the header");
        }

        // Validate Log Out link is displayed
        WebElement logout = driver.findElement(logOutLink);
        if (!logout.isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in the header");
        }

        // Validate Settings icon is displayed (if applicable)
        WebElement settings = driver.findElement(settingsIcon);
        if (!settings.isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in the header");
        }

        // Validate User icon is displayed (if applicable)
        WebElement user = driver.findElement(userIcon);
        if (!user.isDisplayed()) {
            throw new AssertionError("User icon is not visible in the header");
        }
    }
}
