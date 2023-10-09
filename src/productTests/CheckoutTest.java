package productTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Objects;

public class CheckoutTest {


    @Test
    public void checkoutPositive () {

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkout();
        cartPage.checkoutInfo("Pera","Peric","11080");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html", "Positive test FAILED!");

        driver.quit();

    }

    @Test
    public void checkoutNegative () {

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkout();
        cartPage.checkoutInfo("","","");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "Negative Test Passed!");

        driver.quit();

    }

    @Test
    public void checkoutPriceTotal () {

        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.checkout();
        cartPage.checkoutInfo("Pera","Peric","11080");


        if(Objects.equals(cartPage.returnProductsPrice(), cartPage.returnSubTotal())){
            System.out.println("Prices are equal");
        }
        else {
            System.out.println("Prices are not equal");
        }

        driver.quit();

    }

}
