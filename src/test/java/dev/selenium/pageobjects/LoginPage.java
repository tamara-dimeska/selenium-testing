package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    // TODO make the By.cssSelector more generic
    @FindBy(css = "[data-test='login-button']")
    WebElement loginButton;
    @FindBy(css = "[data-test='username']")
    WebElement usernameField;
    @FindBy(css = "[data-test='password']")
    WebElement passwordField;
    @FindBy(css = "[data-test='error']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        // Initialize all @FindBy fields
        PageFactory.initElements(driver, this);
    }

    public BasePage login(String username, String password) {
        // TODO create user class/interface
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        if (ElementUtils.isElementNotDisplayed(loginButton)) {
            return new HomePage(driver);
        } else {
            return this;
        }
    }

    public boolean isLoginButtonDisplayed() {
       return ElementUtils.isElementDisplayed(loginButton);
    }

    public boolean isErrorMessageDisplayed() {
        return ElementUtils.isElementDisplayed(errorMessage);
    }
}
