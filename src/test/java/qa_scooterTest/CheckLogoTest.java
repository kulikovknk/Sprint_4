package qa_scooterTest;

import org.junit.Assert;
import org.junit.Test;
import qa_scooter.ScooterHomePage;

public class CheckLogoTest extends BaseUITest {

    @Test
    public void checkScooterLogoTest() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();
        objScooterHomePage.clickScooterLogo();

        boolean isCurrentPageEqualsHomePage = objScooterHomePage.checkCurrentPageEqualsHomePage();

        Assert.assertTrue("Тест \"Нажатие на логотип \"Самокат\". Текущая страница не соответствует главной странице сервиса.", isCurrentPageEqualsHomePage);
    }

    @Test
    public void checkYandexLogoTest() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();
        objScooterHomePage.clickYandexLogo();

        boolean isCurrentPageIsYandexHomePage = objScooterHomePage.checkCurrentPageIsYandexHomePage();
        Assert.assertTrue("Тест \"Нажатие на логотип \"Яндекс\". Текущая страница не соответствует главной странице Яндекса.", isCurrentPageIsYandexHomePage);

    }
}
