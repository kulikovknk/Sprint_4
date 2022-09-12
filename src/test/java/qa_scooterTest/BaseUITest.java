package qa_scooterTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;

public abstract class BaseUITest {

    protected static WebDriver driver;

    @BeforeClass
    public static void startUp() {

//      WebDriverManager.chromedriver().setup();
//      driver = new ChromeDriver();

      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();

    }

    @After
    public void clearData() {

        driver.manage().deleteAllCookies();
        ((WebStorage) driver).getSessionStorage().clear();
        ((WebStorage) driver).getLocalStorage().clear();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
