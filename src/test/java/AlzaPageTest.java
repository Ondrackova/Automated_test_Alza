import alza.cz.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlzaPageTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    //add time for waiting 5 s
    WebDriverWait browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));

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

    @AfterEach
    void afterTest () {
        browser.quit();
    }

    @Test
    void buyTelevision () {

        mainSection.mainMenuTVAudioVideo();
        secondSection.TvAudioVideoSection();
        pageOperations.ascSortingTV();

        //close panel for helping
        WebElement helpingPanel = pageOperations.helpingPanelClose();

        if (helpingPanel.isDisplayed()) {
            helpingPanel.click();
        }

        tvSection.selectTv();

        //variables for expected name of item
        var expectedName = "Televize" + " " + browser.findElement
                        (By.cssSelector(".h1-placeholder"))
                .getText();

        cartOperations.addToCart();

        helpingPanel = pageOperations.helpingPanelClose();
        if (helpingPanel.isDisplayed()) {
            helpingPanel.click();
        }

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

        pomCartPrice1 = pomCartPrice1.replaceAll
                ("\\D", "");    // \\D = any non-numeric character, \\d = numbers 0-9
        int cartPrice1 = Integer.parseInt(pomCartPrice1);

        Assertions.assertEquals(expectedName,actualName);

        System.out.println("Name of item in the cart: ");
        System.out.println(actualName);

        cartOperations.addOneItem();

        //what to do if countPlus is disabled
        if (!browser.findElements(By.cssSelector(".countPlus.disabled")).isEmpty()) {
            WebElement disabledConPlus = browserWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".countPlus.disabled"))
            );
            System.out.println("This item is only for 1.");
            System.out.println("Cannot add more than 1 piece to cart.");
            return;
        }

        //cart price - two items
        var pomCartPrice2 = browserWait.until
                        (ExpectedConditions.elementToBeClickable(By.cssSelector(".last.price")))
                .getText();

        pomCartPrice2 = pomCartPrice2.replaceAll
                ("\\D", "");

        int cartPrice2 = Integer.parseInt(pomCartPrice2);

        Assertions.assertEquals(cartPrice1 * 2, cartPrice2);

        System.out.println("Price for one item: ");
        System.out.println(cartPrice1); //write number for one item in terminal
        System.out.println("Price for two item: ");
        System.out.println(cartPrice2); //write number for two item in terminal
    }
}