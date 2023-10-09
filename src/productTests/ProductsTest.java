package productTests;

import models.Products;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsTest {

    @Test
    public void verifyAddProductToCart(){

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage ProductsPage = new ProductsPage(driver);
        Integer beforeAdd = ProductsPage.returnItemNumber(driver);
        ProductsPage.addProductToCartByName("Sauce Labs Backpack");
        Integer afterAdd = ProductsPage.returnItemNumber(driver);

        if (afterAdd == beforeAdd + 1) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        driver.quit();

    }

    @Test
    public void verifyItemNameInCart () {

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        List<String> itemsToBeAdded = new ArrayList<>();
        itemsToBeAdded.add("Sauce Labs Backpack");

        ProductsPage productsPage = new ProductsPage(driver);
        for (int i=0; i<itemsToBeAdded.size(); i++){
            productsPage.addProductToCartByName(itemsToBeAdded.get(i));
        }
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        List<Products> cartItems = cartPage.itemsInCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals( cartItems, itemsToBeAdded, "Item with expected name is not added to cart");

        driver.quit();

    }

    @Test
    public void verifyItemPriceInCart () {

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        List<Double> itemsToBeAdded = new ArrayList<>();
        itemsToBeAdded.add(29.99);

        ProductsPage productsPage = new ProductsPage(driver);
        for (int i=0; i<itemsToBeAdded.size(); i++){
            productsPage.addProductToCartByPrice(itemsToBeAdded.get(i));
        }
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        List<Products> cartItems = cartPage.itemsInCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals( cartItems, itemsToBeAdded,"Item with expected price is not added to cart");

        driver.quit();

    }

    @Test
    public void verifyItemDescriptionInCart (){

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        List<String> itemsToBeAdded = new ArrayList<>();
        itemsToBeAdded.add("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        ProductsPage productsPage = new ProductsPage(driver);
        for (int i=0; i<itemsToBeAdded.size(); i++){
            productsPage.addProductToCartByDsc(itemsToBeAdded.get(i));
        }
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        List<Products> cartItems = cartPage.itemsInCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals( cartItems, itemsToBeAdded,"Item with expected description is not added to cart");

        driver.quit();

    }

}
