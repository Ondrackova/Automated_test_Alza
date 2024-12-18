package alza.cz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondSection {
    WebDriver browser;
    WebDriverWait browserWait;

    //initialization of browser
    public SecondSection (WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }

    public void TvAudioVideoSection() {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//a[@href='https://www.alza.cz/televize/18849604.htm']")))
                .click();
    }

}
