package qa_scooter;

import com.sun.jna.Structure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.Struct;

public class OrderStatusPage extends ScooterBasePage {

    //Форма подтверждения заказа
    //Форма "Хотите оформить заказ?"
    private final String orderStatusPageHeaderText = "Заказ оформлен";
    private final By orderStatusPageHeader = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    //Конструктор класса
    public OrderStatusPage(WebDriver driver) {super(driver); }

    //Нажать на кнопку "Посмотреть статус"
    public boolean checkOrderStatusPage() {

        //Если после в заголовке формы нет сообщения об успешном создании заказа, заказ не оформлен
        if(!driver.findElement(orderStatusPageHeader).getText().contains(orderStatusPageHeaderText)) {
            return false;
        }
        return true;

    }

}


