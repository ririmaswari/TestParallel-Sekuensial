package com.juaracoding.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // === Locators ===
    private By addCartItemXpath = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button");
    private By buttonCartXpath = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private By totalItemCartXpath = By.className("shopping_cart_badge");
    private By totalItemCartBackupXpath = By.xpath("//*[@id='shopping_cart_container']/a/span");
    private By removeItemButtonXpath = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
    private By checkoutButtonXpath = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]");
    public static By allProductItems = By.cssSelector(".inventory_item");
    private By addToCartButtons = By.cssSelector(".btn_inventory");
    public static By inventoryItemName = By.xpath("//div[@class='inventory_item_name']");
    public static By productSortContainer = By.xpath("//select[@class='product_sort_container']");


    // === Constructor ===
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // === Actions ===
    public void addItemToCart() {
        driver.findElement(addCartItemXpath).click();
    }

    public void openCart() {
        driver.findElement(buttonCartXpath).click();
    }

    public String getCartItemCount() {
        try {
            // Try primary locator first
            WebElement cartBadge = driver.findElement(totalItemCartXpath);
            return cartBadge.getText();
        } catch (Exception e) {
            try {
                // Try backup locator
                WebElement cartBadge = driver.findElement(totalItemCartBackupXpath);
                return cartBadge.getText();
            } catch (Exception ex) {
                return "0"; // Return 0 if no badge found (empty cart)
            }
        }
    }

    public int getCartItemCountAsInt() {
        String countText = getCartItemCount();
        try {
            return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void removeItemFromCart() {
        driver.findElement(removeItemButtonXpath).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButtonXpath).click();
    }

    public int getTotalProductDisplayed() {
        List<WebElement> products = driver.findElements(allProductItems);
        return products.size();
    }

    public void addMultipleItemsToCart(int totalItems) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (int i = 0; i < totalItems && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }
}
