package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (using provided locators and descriptive placeholders as needed)
    private By contactsLink = By.cssSelector("a[href='/contacts']");
    private By createButton = By.xpath("//button[contains(@class, 'ui linkedin button') and .//i[contains(@class, 'edit icon')]]");
    private By settingsIcon = By.xpath("//i[contains(@class, 'settings icon')]");
    private By userIcon = By.xpath("//i[contains(@class, 'large user red icon')]");
    private By logOutLink = By.xpath("//a[contains(., 'Log Out')]");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Validate that all header components are visible and accessible")
    public boolean validateHeaderComponentsVisible() {
        boolean allVisible = true;
        try {
            WebElement contacts = driver.findElement(contactsLink);
            WebElement create = driver.findElement(createButton);
            WebElement settings = driver.findElement(settingsIcon);
            WebElement user = driver.findElement(userIcon);
            WebElement logout = driver.findElement(logOutLink);
            allVisible = contacts.isDisplayed() && create.isDisplayed() && settings.isDisplayed() && user.isDisplayed() && logout.isDisplayed();
        } catch (Exception e) {
            allVisible = false;
        }
        return allVisible;
    }
}
