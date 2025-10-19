package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartNavBar extends BasePage {
    @FindBy(css = "[data-test='shopping-cart-icon']")
    BaseElement shoppingCartIcon;
    @FindBy(css = "[data-test='items-in-cart']")
    BaseElement itemsInCartLabel;

    public CartNavBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage openShoppingCart() {
        shoppingCartIcon.click();

        return new ShoppingCartPage(driver);
    }

    public boolean isTextDisplayedInCartIcon(String itemNo) {
        return itemsInCartLabel.isTextPresent(itemNo);
    }

    public boolean isItemsInCartDisplayed() {
        return itemsInCartLabel.isDisplayed();
    }
}
