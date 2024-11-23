package alza.cz;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlzaTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    //add time for waiting 5 s
    WebDriverWait browserWait = new WebDriverWait(browser, Duration.ofSeconds(3));

    MainSection mainSection;

    @BeforeEach
    void beforeTest () {
        //open the web page alza.cz
        browser.get("https://www.alza.cz/");

        //reject cookies
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector
                                        (".cookies-info__button.cookies-info__button--link.js-cookies-info-reject")))
                .click();

        mainSection = new MainSection(browser);
    }

    @Test
    void buyTelevision () {

        //click on TV, audio, video section
       mainSection.mainMenuTVAudioVideo();

        //click on TV section
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[@href='https://www.alza.cz/televize/18849604.htm']")))
                .click();

        //sort from cheapest tv
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("ui-id-3"))).
                click();

        //click on cheapest TV
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[@href='/ecg-24-h05t2s2-d9911329.htm']"))).
                click();


        //variables for expected name of item
        var expectedName = "Televize" + " " + browser.findElement
                (By.cssSelector(".h1-placeholder"))
                .getText();

        //Add item to the cart
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".price-detail__buy-actions")))
                .click();

        //close panel for helping
        browserWait.until(ExpectedConditions.elementToBeClickable
                        (By.id("chat-open-button")))
                .click();

        //go to the cart
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("varBToBasketButton")))
                .click();


        //variable for actual name of item in the basket
        var actualName = browserWait.until
                (ExpectedConditions.elementToBeClickable
                (By.cssSelector(".mainItem")))
                .getText();

        //cart price for one item
        var pomCartPrice1 = browserWait.until
                (ExpectedConditions.elementToBeClickable(By.cssSelector(".last.price")))
                .getText();

        // remove non-numeric characters if necessary
        pomCartPrice1 = pomCartPrice1.replaceAll("\\D", ""); // Removes $, €, etc.

        // Convert the string to an integer
        int cartPrice1 = Integer.parseInt(pomCartPrice1);

        //assert item in the cart
        Assertions.assertEquals(expectedName,actualName);

        //write output to the terminal
        System.out.println("Name of item in the cart: ");
        System.out.println(actualName); //write name of item in terminal

        //add one item of the same television
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".countPlus")))
                .click();

        //cart price - two items
        var pomCartPrice2 = browserWait.until
                        (ExpectedConditions.elementToBeClickable(By.cssSelector(".last.price")))
                .getText();

        // remove non-numeric characters if necessary
        pomCartPrice2 = pomCartPrice2.replaceAll("\\D", ""); // Removes $, €, etc.

        // convert the string to an integer
        int cartPrice2 = Integer.parseInt(pomCartPrice2);

        //assert double prices in the cart
        Assertions.assertEquals(cartPrice1 * 2, cartPrice2);

        //write output to the terminal
        System.out.println("Price for one item: ");
        System.out.println(cartPrice1); //write number for one item in terminal
        System.out.println("Price for two item: ");
        System.out.println(cartPrice2); //write number for two item in terminal
    }

}
