import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    WebDriver browser = WebDriverManager.firefoxdriver()
            .create();
    //add time for waiting 5 s
    WebDriverWait browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));

    @BeforeEach
    void beforeTest () {
        //open the web page alza.cz
        browser.get("https://www.alza.cz/");
    }
}
