package alza.cz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TvSection {

    WebDriver browser;
    WebDriverWait browserWait;

    public TvSection (WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait(browser, Duration.ofSeconds(4));
    }

    //click on cheapest TV
    public void selectTv() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='img12632048']"))).
                click();
    }
}

