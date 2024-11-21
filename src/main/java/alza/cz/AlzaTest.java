package alza.cz;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void buyTelevision () {

        //open web page alza.cz
        browser.get("https://www.alza.cz/");

        //reject cookies
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".cookies-info__button.cookies-info__button--link.js-cookies-info-reject")))
                .click();

        //click on TV, audio, video section
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("litp18852655")))
                .click();

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
        var expectedName = browser.findElement
                (By.cssSelector(".h1-placeholder"))
                .getText();

        //Add item to the cart
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[@href='javascript:detailOrder();")))
                .click();

        //variables for actual name of item
        var actualName = browser.findElement
                (By.id("order-item-679752367")).
                getText();

        //assert item in the cart
        Assertions.assertEquals(expectedName,actualName);
    }


}
