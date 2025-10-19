package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(css = "[data-test='error']")
    BaseElement error;
    @FindBy(xpath = "//*[text()='Thank you for your order!']")
    BaseElement title;
    @FindBy(css = "[data-test='firstName']")
    BaseElement firstNameField;
    @FindBy(css = "[data-test='lastName']")
    BaseElement lastNameField;
    @FindBy(css = "[data-test='postalCode']")
    BaseElement postCodeField;
    @FindBy(css = "[data-test='continue']")
    BaseElement continueButton;
    @FindBy(css = "[data-test='cancel']")
    BaseElement cancelButton;
    @FindBy(css = "[data-test='finish']")
    BaseElement finishButton;
    @FindBy(css = "[data-test='back-to-products']")
    BaseElement backButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
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
        finishButton.click();

        return this;
    }

    public CheckoutPage clickBackButton() {
        backButton.click();

        return this;
    }

    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    public boolean isErrorMessageDisplayed(String text) {
        return error.isTextPresent(text);
    }
}
