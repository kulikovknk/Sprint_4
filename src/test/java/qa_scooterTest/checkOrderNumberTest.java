package qa_scooterTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa_scooter.ScooterHomePage;

@RunWith(Parameterized.class)
public class checkOrderNumberTest extends BaseUITest {

    //Поля тестового класса
    private final String orderNumber;

    //Конструктор тестового класса
    public checkOrderNumberTest(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getOrderNumbers() {
        return new Object[][] {
                {"1234"},
                {"234577"},
        };
    }

    @Test
    public void checkOrderNumberTest() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();
        objScooterHomePage.inputOrderNumber(orderNumber);

        boolean isOrderWithWrongNumberNotFound = objScooterHomePage.checkOrderStatusWithWrongNumber();

        Assert.assertTrue("Если ввести неправильный номер заказа, на странице статуса заказа должно быть написано, что такого заказа нет.", isOrderWithWrongNumberNotFound);
    }
}
