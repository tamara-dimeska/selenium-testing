package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    @FindBy(css = "[data-test='error']")
    WebElement error;
    @FindBy(xpath = "//*[text()='Thank you for your order!']")
    WebElement title;
    @FindBy(css = "[data-test='firstName']")
    WebElement firstNameField;
    @FindBy(css = "[data-test='lastName']")
    WebElement lastNameField;
    @FindBy(css = "[data-test='postalCode']")
    WebElement postCodeField;
    @FindBy(css = "[data-test='continue']")
    WebElement continueButton;
    @FindBy(css = "[data-test='cancel']")
    WebElement cancelButton;
    @FindBy(css = "[data-test='finish']")
    WebElement finishButton;
    @FindBy(css = "[data-test='back-to-products']")
    WebElement backButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage fillInFirstName(String firstName) {
        firstNameField.sendKeys(firstName);

        return this;
    }

    public CheckoutPage fillInLastName(String lastName) {
        lastNameField.sendKeys(lastName);

        return this;
    }

    public CheckoutPage fillInPostCode(String postCode) {
        postCodeField.sendKeys(postCode);

        return this;
    }

    public CheckoutPage fillInForm(String firstName, String lastName, String postCode) {
        return fillInFirstName(firstName).
                fillInLastName(lastName).
                fillInPostCode(postCode);
    }

    public CheckoutPage clickContinueButton() {
        continueButton.click();

        return this;
    }

    public ShoppingCartPage clickCancelButton() {
        cancelButton.click();

        return new ShoppingCartPage(driver);
    }

    public CheckoutPage clickFinishButton() {
        ElementUtils.clickWhenReady(finishButton);

        return this;
    }

    public CheckoutPage clickBackButton() {
        ElementUtils.clickWhenReady(backButton);

        return this;
    }

    public boolean isTitleDisplayed() {
        return ElementUtils.isElementDisplayed(title);
    }

    public boolean isErrorMessageDisplayed(String text) {
        return ElementUtils.isTextPresent(error, text);
    }
}
