package qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage extends ScooterBasePage {

    private final By orderStatusPageHeader = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    //Конструктор класса
    public OrderStatusPage(WebDriver driver) {super(driver); }

    //Нажать на кнопку "Посмотреть статус"
    public boolean checkOrderStatusPage() {

        //Если после в заголовке формы нет сообщения об успешном создании заказа, заказ не оформлен
        //Форма подтверждения заказа
        //Форма "Хотите оформить заказ?"
        String orderStatusPageHeaderText = "Заказ оформлен";
        return driver.findElement(orderStatusPageHeader).getText().contains(orderStatusPageHeaderText);

    }

}


