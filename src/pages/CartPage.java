package pages;

import models.Products;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement returnItemList() {
        return  driver.findElement(By.className("cart_list"));
    }

    public void openCart () {
        WebElement openCartLink = driver.findElement(By.className("shopping_cart_link"));
        openCartLink.click();
    }

    public List<Products> itemsInCart () {
        List<Products> productsInCart = new ArrayList<>();
        WebElement cartList = driver.findElement(By.className("cart_list"));
        List<WebElement> cartItem = cartList.findElements(By.className("cart_item"));

        for (int i=0; i<cartItem.size(); i++){
            WebElement inventoryItemName = cartItem.get(i).findElement(By.className("inventory_item_name"));
            String name = inventoryItemName.getText();

            WebElement inventoryItemPrice = cartItem.get(i).findElement(By.className("inventory_item_price"));
            double price = Double.parseDouble(inventoryItemPrice.getText().substring(1));

            WebElement inventoryItemDsc = cartItem.get(i).findElement(By.className("inventory_item_desc"));
            String dsc = inventoryItemDsc.getText();

            Products product = new Products(name, price, dsc);
            productsInCart.add(product);

        }
        return productsInCart;
    }

    public void checkout () {
        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        buttonCheckout.click();
    }

    public void checkoutInfo (String firstName, String lastName, String zip) {

        WebElement inputFirstName = driver.findElement(By.id("first-name"));
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);

        WebElement inputLastName = driver.findElement(By.id("last-name"));
        inputLastName.clear();
        inputLastName.sendKeys(lastName);

        WebElement inputZip = driver.findElement(By.id("postal-code"));
        inputZip.clear();
        inputZip.sendKeys(zip);

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

    }

    public List<WebElement> returnCartItemsPrice() {
        WebElement inventoryList = returnItemList();
        return inventoryList.findElements(By.className("cart_item"));
    }

    public Double returnProductsPrice() {
        List<Double> priceList = new ArrayList<>();
        List<WebElement> inventoryItems = returnCartItemsPrice();

        for (int i = 0; i < inventoryItems.size(); i++){
            WebElement itemPrice = inventoryItems.get(i).findElement(By.className("inventory_item_price"));
            String price = itemPrice.getText();
            priceList.add(Double.parseDouble(price.substring(1)));
        }

        double sum = 0;

        for (int j=0; j<priceList.size(); j++){
            sum += priceList.get(j);
        }
        return sum;
    }

    public Double returnSubTotal() {
        double priceList;
        WebElement itemPrice = driver.findElement(By.className("summary_subtotal_label"));
        String price = itemPrice.getText();
        priceList = Double.parseDouble(price.substring(13,18));
        return priceList;
    }
}
