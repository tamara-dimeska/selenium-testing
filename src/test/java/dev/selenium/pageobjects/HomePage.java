package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.By;
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
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    WebElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    WebElement removeBackpackToCartButton;

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

    public ItemPage openItem(String itemLabel) {
        WebElement item = driver.findElement(By.xpath("//*[contains(text(), '" + itemLabel + "')]"));

        ElementUtils.clickWhenReady(item);

        return new ItemPage(driver);
    }

    public HomePage addBackpackToCart() {
        ElementUtils.clickWhenReady(addBackpackToCartButton);

        return this;
    }

    public HomePage removeBackpackToCart() {
        ElementUtils.clickWhenReady(removeBackpackToCartButton);

        return this;
    }
}
