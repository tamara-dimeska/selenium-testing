package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "//*[text()='Your Cart']")
    BaseElement title;
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    BaseElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    BaseElement removeBackpackToCartButton;
    @FindBy(css = "[data-test='checkout']")
    BaseElement checkoutButton;
    @FindBy(css = "[data-test='continue-shopping']")
    BaseElement continueShoppingButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isItemDisplayed(String itemLabel) {
        try {
            By locator = By.xpath("//*[contains(text(), '" + itemLabel + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ShoppingCartPage addBackpackToCart() {
        addBackpackToCartButton.click();

        return this;
    }

    public ShoppingCartPage removeBackpackToCart() {
        removeBackpackToCartButton.click();

        return this;
    }

    public CheckoutPage openCheckout() {
        checkoutButton.click();

        return new CheckoutPage(driver);
    }

    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    public HomePage clickContinueShopping() {
        continueShoppingButton.click();

        return new HomePage(driver);
    }
}
