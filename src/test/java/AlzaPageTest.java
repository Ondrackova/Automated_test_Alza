import alza.cz.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlzaPageTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    //add time for waiting 5 s
    WebDriverWait browserWait = new WebDriverWait(browser, Duration.ofSeconds(3));

    MainSection mainSection;
    SecondSection secondSection;
    TvSection tvSection;
    PageOperations pageOperations;
    CartOperations cartOperations;

    @BeforeEach
    void beforeTest () {
        //open the web page alza.cz
        browser.get("https://www.alza.cz/");

        mainSection = new MainSection(browser);
        secondSection = new SecondSection(browser);
        tvSection = new TvSection(browser);
        pageOperations = new PageOperations(browser);
        cartOperations = new CartOperations(browser);

        //reject cookies
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector
                                        (".cookies-info__button.cookies-info__button--link.js-cookies-info-reject")))
                .click();

    }

    @Test
    void buyTelevision () {

        //click on TV, audio, video section
        mainSection.mainMenuTVAudioVideo();

        //click on TV section
        secondSection.TvAudioVideoSection();

        //sort from cheapest tv
        pageOperations.ascSortingTV();

        //click on cheapest TV
        tvSection.selectTv();

        //variables for expected name of item
        var expectedName = "Televize" + " " + browser.findElement
                        (By.cssSelector(".h1-placeholder"))
                .getText();

        //Add item to the cart
        cartOperations.addToCart();

        //close panel for helping
        WebElement helpingPanel = pageOperations.helpingPanelClose();
        if (helpingPanel == null) {
            System.out.println("Element helpingPanel not found!");
        } else {
            helpingPanel.click();
        }

        //go to the cart
        cartOperations.goToCart();

        //variable for actual name of item in the basket
        var actualName = browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector(".mainItem")))
                .getText();

        //cart price for one item
        var pomCartPrice1 = browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector(".last.price")))
                .getText();

        // remove non-numeric characters if necessary
        pomCartPrice1 = pomCartPrice1.replaceAll
                ("\\D", ""); // Removes $, €, etc.

        // Convert the string to an integer
        int cartPrice1 = Integer.parseInt(pomCartPrice1);

        //assert item in the cart
        Assertions.assertEquals(expectedName,actualName);

        //write output to the terminal
        System.out.println("Name of item in the cart: ");
        //write name of item in terminal
        System.out.println(actualName);

        //add one item of the same television
        cartOperations.addOneItem();

        //variable for present of disabled button
        var disabledConPlus = browserWait.until
                (ExpectedConditions.presenceOfElementLocated(By.cssSelector(".countPlus.disabled")));

        //condition for present of disabled button
        if(disabledConPlus.isDisplayed()) {
            // if yes, write a message
            System.out.println("This item is only for 1.");

            // end the test
            System.out.println("Cannot add more than 1 piece to cart.");
            return; // end the test
        }

        //cart price - two items
        var pomCartPrice2 = browserWait.until
                        (ExpectedConditions.elementToBeClickable(By.cssSelector(".last.price")))
                .getText();

        // remove non-numeric characters if necessary
        pomCartPrice2 = pomCartPrice2.replaceAll
                ("\\D", ""); // Removes $, €, etc.

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