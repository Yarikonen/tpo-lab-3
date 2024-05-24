package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final Duration duration = Duration.ofSeconds(5L);

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, duration);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void loginHeader() {
        driver.get("https://mix.com");

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'text')]")));

        Assertions.assertTrue(header.getText().contains("Expand")&& header.getText().contains("your mind"));
    }

    @Test
    public void testLoginWays() throws InterruptedException {
        driver.get("https://mix.com");
        Thread.sleep(2000);

        var expectedWays= Arrays.asList("Facebook", "Twitter", "Google", "Apple");

        WebElement joinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Join Mix\"]")));

        joinButton.click();

        List<WebElement> ways = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//form"), 4));

//        for (int i = 0; i < 4; i++) {
//            System.out.println(ways.get(i).getText());
//            Assertions.assertTrue((expectedWays.contains(ways.get(i).getText())));
//        }
    }



}
