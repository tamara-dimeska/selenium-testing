package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartNavBar extends BasePage {
    @FindBy(css = "[data-test='shopping-cart-icon']")
    WebElement shoppingCartIcon;
    @FindBy(css = "[data-test='items-in-cart']")
    WebElement itemsInCartLabel;

    public CartNavBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage openShoppingCart() {
        ElementUtils.clickWhenReady(shoppingCartIcon);

        return new ShoppingCartPage(driver);
    }

    public boolean isTextDisplayedInCartIcon(String itemNo) {
        return ElementUtils.isTextPresent(itemsInCartLabel, itemNo);
    }

    public boolean isItemsInCartDisplayed() {
        return ElementUtils.isElementDisplayed(itemsInCartLabel);
    }
}
