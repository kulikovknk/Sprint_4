package qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScooterOrderDetailsPage extends ScooterBasePage {

    //Форма ввода деталей заказа
    //Заголовок формы
    private final String orderHeaderText = "Про аренду";
    private final By orderHeader = By.xpath(".//div[@class = 'Order_Header__BZXOb']");
    //Поле "Когда привезти самокат"
    private final By fieldRentalDate = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Когда привезти самокат']");
    //Поле "Срок аренды"
    private final By fieldRentalPeriod = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[@class = 'Dropdown-root']//div[@class = 'Dropdown-arrow-wrapper']//span[@class = 'Dropdown-arrow']");
    //Поле "Цвет самоката"
    private final By fieldScooterColor = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[@class = 'Order_Checkboxes__3lWSI']");
    //Поле "Комментарий для курьера"
    private final By fieldOrderComment = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = 'Комментарий для курьера']");
    //Сообщение об ошибке поля "Комментарий для курьера"
    private final By fieldOrderCommentErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Тут что-то не так']");
    //Кнопка "Заказать"
    private final By buttonOrder = By.xpath(".//div[@class = 'Order_Content__bmtHS']//button[text() = 'Заказать']");

    //Конструктор класса
    public ScooterOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    //Заполнение полей формы уточнения данных для аренды
    public boolean inputOrderDetails(String rentalDate, String rentalPeriod, String scooterColor, String comment) {

        driver.findElement(fieldRentalDate).sendKeys(rentalDate);
        driver.findElement(fieldOrderComment).sendKeys(comment);

        //Заполняем поле "Срок аренды"
        if (!rentalPeriod.isEmpty()) {
            driver.findElement(fieldRentalPeriod).click();
            WebElement webElementRentalPeriod = driver.findElement(By.xpath(String.format(".//*[text() = '%s']", rentalPeriod)));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView();", webElementRentalPeriod);
            webElementRentalPeriod.click();
        }

        //заполнение поля "Цвет самоката"
        if (!scooterColor.isEmpty()) {
            driver.findElement(fieldScooterColor).findElement(By.xpath(String.format("//label[text() = '%s']", scooterColor))).click();
        }

        driver.findElement(buttonOrder).click();

        //Если после нажатия "Заказать" заголовок формы остался прежним, проверим ошибки ввода данных
        if(driver.findElement(orderHeader).getText().contains(orderHeaderText)) {
            if (driver.findElement(fieldOrderCommentErrorMessage).isDisplayed()) {
                return false;
            }
        }

        return true;

    }
}
