package alza.cz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartOperations {
    WebDriver browser;
    WebDriverWait browserWait;

    public CartOperations (WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }

    //Add item to the cart
    public void addToCart() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector(".price-detail__buy-actions")))
                .click();
    }
    //go to the cart
    public void goToCart() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("varBToBasketButton")))
                .click();
    }
    //add one item in the cart
    public void addOneItem() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector(".countPlus")))
                .click();
    }

}
