package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(css = "[data-test='title']")
    BaseElement title;
    @FindBy(xpath = "//*[text()='Open Menu']")
    BaseElement menuButton;
    @FindBy(css = "[data-test='logout-sidebar-link']")
    BaseElement logoutButton;
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    BaseElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    BaseElement removeBackpackToCartButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage logout() {
        menuButton.click();
        logoutButton.click();

        return new LoginPage(driver);
    }

    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    public ItemPage openItem(String itemLabel) {
        WebElement item = driver.findElement(By.xpath("//*[contains(text(), '" + itemLabel + "')]"));

        item.click();

        return new ItemPage(driver);
    }

    public HomePage addBackpackToCart() {
        addBackpackToCartButton.click();

        return this;
    }

    public HomePage removeBackpackToCart() {
        removeBackpackToCartButton.click();

        return this;
    }
}
