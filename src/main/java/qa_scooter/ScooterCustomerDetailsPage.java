package qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScooterCustomerDetailsPage extends ScooterBasePage {

    //Форма заполнения данных о заказчике
    //Заголовок формы
    private final String orderHeaderText = "Для кого самокат";
    private final By orderHeader = By.xpath(".//div[@class = 'Order_Header__BZXOb']");
    //Поле "Имя"
    private final By inputName = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Имя']");
    //Сообщение об ошибке при заполнении поля "Имя"
    private final By inputNameErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Введите корректное имя']");
    //Поле "Фамилия"
    private final By inputSurname = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Фамилия']");
    //Сообщение об ошибке при заполнении поля "Фамилия"
    private final By inputSurnameErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Введите корректную фамилию']");
    //Поле "Адрес"
    private final By inputAddress = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Сообщение об ошибке при заполнении поля "Адрес"
    private final By inputAddressErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Введите корректный адрес']");
    //Поле "Станция метро"
    private final By inputMetroStation = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Станция метро']");
    //Сообщение об ошибке при заполнении поля "Станция метро"
    private final By inputMetroStationErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Выберите станцию']");
    //Поле "Номер телефона"
    private final By inputPhoneNumber = By.xpath(".//div[@class = 'Order_Form__17u6u']//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Сообщение об ошибке при заполнении поля "Номер телефона"
    private final By inputPhoneNumberErrorMessage = By.xpath(".//div[@class = 'Order_Form__17u6u']//div[text() = 'Введите корректный номер']");

    //Кнопка "Далее"
    private final By orderNextButton = By.xpath(".//div[@class = 'Order_Content__bmtHS']//button[text() = 'Далее']");

    //Конструктор класса
    public ScooterCustomerDetailsPage(WebDriver driver) {
        super(driver);
    }

    //Заполнение полей формы уточнения данных о заказчике
    public boolean inputCustomerDetails(String customerName, String customerSurname, String customerAddress, String customerMetroStation, String customerPhoneNumber) {

        driver.findElement(inputName).sendKeys(customerName);
        driver.findElement(inputSurname).sendKeys(customerSurname);
        driver.findElement(inputAddress).sendKeys(customerAddress);
        driver.findElement(inputPhoneNumber).sendKeys(customerPhoneNumber);

        //Заполняем поле "Станция метро"
        if (!customerMetroStation.isEmpty()) {
            driver.findElement(inputMetroStation).click();
            WebElement metroStation = driver.findElement(By.xpath(String.format(".//*[text() = '%s']", customerMetroStation)));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(String.format(".//*[text() = '%s']", customerMetroStation))));
            metroStation.click();
        }

        driver.findElement(orderNextButton).click();

        //Если после нажатия "Далее" заголовок формы остался прежним, проверим ошибки ввода данных
        if(driver.findElement(orderHeader).getText().contains(orderHeaderText)) {
            if (driver.findElement(inputNameErrorMessage).isDisplayed() ||
                    driver.findElement(inputSurnameErrorMessage).isDisplayed() ||
                    driver.findElement(inputAddressErrorMessage).isDisplayed() ||
                    driver.findElement(inputPhoneNumberErrorMessage).isDisplayed() ||
                    driver.findElement(inputMetroStationErrorMessage).isDisplayed()) {
                return false;
            }
        }

        return true;

    }
}



