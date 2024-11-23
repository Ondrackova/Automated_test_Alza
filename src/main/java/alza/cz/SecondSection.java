package alza.cz;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondSection {
    WebDriver browser;
    WebDriverWait browserWait;

    //inicialization of browser
    public SecondSection (WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }

}
