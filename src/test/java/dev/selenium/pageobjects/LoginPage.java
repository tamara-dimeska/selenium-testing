package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import dev.selenium.utils.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(css = "[data-test='login-button']")
    BaseElement loginButton;
    @FindBy(css = "[data-test='username']")
    BaseElement usernameField;
    @FindBy(css = "[data-test='password']")
    BaseElement passwordField;
    @FindBy(css = "[data-test='error']")
    BaseElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        // Initialize all @FindBy fields
        PageFactory.initElements(driver, this);
    }

    public BasePage login(User user) {
        usernameField.sendKeys(user.username());
        passwordField.sendKeys(user.password());
        loginButton.click();

        if (loginButton.isNotDisplayed()) {
            return new HomePage(driver);
        } else {
            return this;
        }
    }

    public boolean isLoginButtonDisplayed() {
       return loginButton.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }
}
