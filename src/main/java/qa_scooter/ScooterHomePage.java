package qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterHomePage extends ScooterBasePage {

    //Ссылка на главную страницу сервиса
    private final String scooterHomePage = "https://qa-scooter.praktikum-services.ru/";
    //Ссылка на главную страницу Яндекса
    private final String yandexHomePage = "https://yandex.ru";

    //Окно Cookies, кнопка "да все привыкли"
    private final By confirmCookiesButton = By.id("rcc-confirm-button");
    //Кпопка "Заказать" вверху страницы
    private final By OrderButtonOnTheTop = By.xpath(".//div[@class = 'Header_Nav__AGCXC']//button[text() = 'Заказать']");
    //Кпопка "Заказать" внизу страницы
    private final By OrderButtonOnTheBottom = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']//button[text() = 'Заказать']");
    //Логотип сервиса Самокат
    private final By scooterLogo = By.xpath(".//a[@class = 'Header_LogoScooter__3lsAR']//img[@alt = 'Scooter']");
    //Логотип Яндекса
    private final By yandexLogo = By.xpath(".//a[@class = 'Header_LogoYandex__3TSOI']//img[@alt = 'Yandex']");
    //Кнопка "Статус заказа"
    private final By buttonOrderStatus = By.xpath(".//div[@class = 'Header_Nav__AGCXC']//button[text() = 'Статус заказа']");
    //Поле для ввода номера заказа
    private final By inputOrderNumber = By.xpath(".//input[@placeholder = 'Введите номер заказа']");
    //Кнопка "Go!"
    private final By buttonGo = By.xpath(".//div[@class = 'Header_SearchInput__3YRIQ']//button[text() = 'Go!']");
    //Изображение "Такого заказа нет"
    private final By orderNotFoundImg = By.xpath(".//div[@class = 'Track_NotFound__6oaoY']//img[@alt = 'Not found']");


    //Конструктор класса
    public ScooterHomePage(WebDriver driver) {
        super(driver);
    }

    //Открыть страницу сервиса
    public void openHomePage() {

        driver.get(scooterHomePage);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));

        //Закрываем окно cookie, так как оно может помешать кликнуть на другие элементы формы
        if (driver.findElement(confirmCookiesButton).isEnabled()) {
            driver.findElement(confirmCookiesButton).click();
        }
    }

    //Проверить что ответ на вопрос соответствует заданному
    public boolean checkThatAnswerIsAsExpected(int questionNumber, String correctAnswer) {

        //Секция с определенным номером вопроса
        By question = By.id(String.format("accordion__heading-%s", (questionNumber-1)));
        //Секция с ответом на вопрос
        By answer = By.xpath(String.format(".//div[@id=\"accordion__panel-%s\"]/p", (questionNumber-1)));

        ((JavascriptExecutor)driver)
                  .executeScript("arguments[0].scrollIntoView();", driver.findElement(question));

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(question)));

        driver.findElement(question).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
              .until(ExpectedConditions.visibilityOf(driver.findElement(answer)));

        return correctAnswer.equals(driver.findElement(answer).getText());
    }

    public void clickUpperOrderButton() {
        driver.findElement(OrderButtonOnTheTop).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(OrderButtonOnTheBottom).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {

        String originalWindow = driver.getWindowHandle();

        driver.findElement(yandexLogo).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle: driver.getWindowHandles()) {

            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.urlContains(yandexHomePage));
                break;
            }
        }
    }

    public boolean checkCurrentPageEqualsHomePage() {
        return driver.getCurrentUrl().contains(scooterHomePage);
    }

    public boolean checkCurrentPageIsYandexHomePage() {
        return driver.getCurrentUrl().contains(yandexHomePage);
    }

    public void inputOrderNumber(String orderNumber) {
        driver.findElement(buttonOrderStatus).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(inputOrderNumber)));

        driver.findElement(inputOrderNumber).sendKeys(orderNumber);
        driver.findElement(buttonGo).click();
    }

    public boolean checkOrderStatusWithWrongNumber() {

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderNotFoundImg)));

        return driver.findElement(orderNotFoundImg).isDisplayed();
    }
}
