package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(css = "[data-test='title']")
    WebElement title;
    @FindBy(xpath = "//*[text()='Open Menu']")
    WebElement menuButton;
    @FindBy(css = "[data-test='logout-sidebar-link']")
    WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage logout() {
        menuButton.click();
        ElementUtils.clickWhenReady(logoutButton);

        return new LoginPage(driver);
    }

    public boolean isTitleDisplayed() {
        return ElementUtils.isElementDisplayed(title);
    }
}
