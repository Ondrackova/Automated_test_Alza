package alza.cz;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class MainSection {
    WebDriver browser;
    WebDriverWait browserWait;

    //browser inicialization
    public MainSection(WebDriver browser) {
        this.browser = browser;
        this.browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }
    //click on TV, audio, video section
    void mainMenuTVAudioVideo () {
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("litp18852655")))
                .click();
    }



}
