package alza.cz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageOperations {

    WebDriver browser;
    WebDriverWait browserWait;

    public PageOperations (WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait
                (browser, Duration.ofSeconds(5));
    }

        //sort from cheapest tv
        public void ascSortingTV() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='tabs']/ul/li[3]")))
                .click();
    }

        //close panel for helping
        public void helpingPanelClose() {

            browserWait.until(ExpectedConditions.presenceOfElementLocated
                    (By.id("chat-open-button")));
        }

}

