package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        cart.click();
    }
    public WebElement returnItemList() {
        return driver.findElement(By.className("inventory_list"));
    }



    public void addProductToCartByName(String product) {
        WebElement inventoryList = returnItemList();
        List<WebElement> inventoryItems = inventoryList.findElements(By.className("inventory_item"));

        for (int i = 0; i < inventoryItems.size(); i++) {
            WebElement inventoryItemName = inventoryItems.get(i).findElement(By.className("inventory_item_name"));

            if (inventoryItemName.getText().equals(product)) {
                WebElement addToCartButton = inventoryItems.get(i).findElement(By.xpath(".//button"));
                addToCartButton.click();
                break;
            }
        }
    }

    public void addProductToCartByPrice(Double product) {

        WebElement inventoryList = returnItemList();
        List<WebElement> inventoryItems = inventoryList.findElements(By.className("inventory_item"));

        for (int i = 0; i < inventoryItems.size(); i++) {
            WebElement priceElement = driver.findElement(By.className("inventory_item_price"));
            String priceText = priceElement.getText();
            Double price = Double.parseDouble(priceText.substring(1));


            if (price.equals(product)) {
                WebElement addToCartButton = inventoryItems.get(i).findElement(By.xpath(".//button"));
                addToCartButton.click();
                break;
            }
        }
    }

    public void addProductToCartByDsc(String product){
        WebElement inventoryList = returnItemList();
        List<WebElement> inventoryItems = inventoryList.findElements(By.className("inventory_item"));

        for (int i = 0; i < inventoryItems.size(); i++) {
            WebElement inventoryItemDsc = inventoryItems.get(i).findElement(By.className("inventory_item_desc"));

            if (inventoryItemDsc.getText().equals(product)) {
                WebElement addToCartButton = inventoryItems.get(i).findElement(By.xpath(".//button"));
                addToCartButton.click();
                break;
            }
        }
    }

    public Integer returnItemNumber(WebDriver driver) {
        Integer toReturn = null;
        WebElement linkShoppingCart = driver.findElement(By.className("shopping_cart_link"));
        List<WebElement> spanItemNumber = linkShoppingCart.findElements(By.className("shopping_cart_badge"));

        if (spanItemNumber.size() == 0) {
            toReturn = 0;
        } else {
            toReturn = Integer.parseInt(spanItemNumber.get(0).getText());
        }
        return toReturn;
    }

}
