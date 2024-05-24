package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final Duration duration = Duration.ofSeconds(15L);

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
    public void testNext() {

        var firstLink = "https://mix.com/!954267045149543424";
        driver.get(firstLink);

        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.//div/span[normalize-space()='Next']]")));

        nextButton.click();
        Assertions.assertNotSame(driver.getCurrentUrl(), firstLink);
    }

    @Test
    public void testTerms() {
        driver.get("https://mix.com/!954267045149543424");

        Actions action = new Actions(driver);

        WebElement who = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@slot='trigger']")));

        action.moveToElement(who).perform();

        WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Terms']")));

        terms.click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("tos"));
    }

    @Test
    public void testPrivacy() {
        driver.get("https://mix.com/!954267045149543424");

        Actions action = new Actions(driver);

        WebElement who = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@slot='trigger']")));

        action.moveToElement(who).perform();

        WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Privacy']")));

        terms.click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("privacy"));
    }

    @Test
    public void testShare(){
        driver.get("https://mix.com/!954267045149543424");
        WebElement shareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div[2]/div[3]/button")));
        shareButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.//div[contains(text(), \"Share\")]]")));
    }

    @Test
    public void testRecommendations(){
        driver.get("https://mix.com/!954267045149543424");
        var you =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div[1]/p")));
        Assertions.assertTrue(you.getText().contains("also like"));
    }


}
