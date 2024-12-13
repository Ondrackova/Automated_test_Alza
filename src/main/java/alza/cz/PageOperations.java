package alza.cz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

            //waiting for loading full page
            WebElement loader = browserWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".circle-loader-container"))
            );
            browserWait.until(ExpectedConditions.invisibilityOf(loader));
        }


        //close panel for helping
        public WebElement helpingPanelClose() {

            browserWait.until(ExpectedConditions.presenceOfElementLocated
                    (By.id("chat-open-button")));
            return browser.findElement(By.id("chat-open-button"));
        }

}

