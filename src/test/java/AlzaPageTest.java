import alza.cz.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlzaPageTest extends BaseTest {

    MainSection mainSection;
    SecondSection secondSection;
    TvSection tvSection;
    PageOperations pageOperations;
    CartOperations cartOperations;

    @BeforeEach
    void beforeTest () {

        super.beforeTest();

        mainSection = new MainSection(browser);
        secondSection = new SecondSection(browser);
        tvSection = new TvSection(browser);
        pageOperations = new PageOperations(browser);
        cartOperations = new CartOperations(browser);

        pageOperations.rejectCookies();
    }

    @AfterEach
    void afterTest () {
        browser.quit();
    }

    @Test
    void buyCheapestTelevision () {

        mainSection.mainMenuTVAudioVideo();
        pageOperations.helpingPanelClose();

        secondSection.TvAudioVideoSection();
        pageOperations.helpingPanelClose();

        pageOperations.ascSortingTV();
        pageOperations.helpingPanelClose();

        tvSection.selectTv();
        pageOperations.helpingPanelClose();

        //variables for expected name of item
        var expectedName = "Televize" + " " + browser.findElement
                        (By.cssSelector(".h1-placeholder"))
                .getText();

        cartOperations.addToCart();
        pageOperations.helpingPanelClose();

        cartOperations.goToCart();
        pageOperations.helpingPanelClose();

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
        pageOperations.helpingPanelClose();

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