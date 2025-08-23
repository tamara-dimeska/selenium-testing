package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "//*[text()='Your Cart']")
    WebElement title;
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    WebElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    WebElement removeBackpackToCartButton;
    @FindBy(css = "[data-test='checkout']")
    WebElement checkoutButton;
    @FindBy(css = "[data-test='continue-shopping']")
    WebElement continueShoppingButton;

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
        ElementUtils.clickWhenReady(addBackpackToCartButton);

        return this;
    }

    public ShoppingCartPage removeBackpackToCart() {
        ElementUtils.clickWhenReady(removeBackpackToCartButton);

        return this;
    }

    public CheckoutPage openCheckout() {
        ElementUtils.clickWhenReady(checkoutButton);

        return new CheckoutPage(driver);
    }

    public boolean isTitleDisplayed() {
        return ElementUtils.isElementDisplayed(title);
    }

    public HomePage clickContinueShopping() {
        ElementUtils.clickWhenReady(continueShoppingButton);

        return new HomePage(driver);
    }
}
