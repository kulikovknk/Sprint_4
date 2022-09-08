package qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends ScooterBasePage {

    //Форма "Хотите оформить заказ?"
    private final String orderConfirmationHeaderText = "Хотите оформить заказ?";
    private final By orderConfirmationHeader = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    // кнопка "Да"
    private final By buttonYesToConfirmOrder = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']//button[text() = 'Да']");

    //Конструктор класса
    public OrderConfirmationPage(WebDriver driver) { super(driver);    }

    //Нажать на кнопку "Да"
    public boolean clickYesToConfirmOrder() {
        driver.findElement(buttonYesToConfirmOrder).click();

        //Если после нажатия "Да" заголовок формы остался прежним, переход на следующую форму не выполнен
        if(driver.findElement(orderConfirmationHeader).getText().contains(orderConfirmationHeaderText)) {
            return false;
        }

        return true;
    }
}
