package qa_scooter;

import org.openqa.selenium.WebDriver;

import java.lang.Thread;

public abstract class ScooterBasePage {

    protected final WebDriver driver;

    public ScooterBasePage(WebDriver driver) {
        this.driver = driver;
    }

//    public void sleep(int duration) {
//
//        try {
//            Thread.sleep(duration);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}


