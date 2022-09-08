package qa_scooterTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import qa_scooter.*;

@RunWith(Parameterized.class)
public class CheckOrderTest extends BaseUITest {

    //Поля тестового класса
    private final String customerName;
    private final String customerSurname;
    private final String customerAddress;
    private final String customerMetroStation;
    private final String customerPhoneNumber;
    private final String rentalDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String orderComment;

    //Конструктор тестового класса
    public CheckOrderTest(String customerName,
                          String customerSurname,
                          String customerAddress,
                          String customerMetroStation,
                          String customerPhoneNumber,
                          String rentalDate,
                          String rentalPeriod,
                          String scooterColor,
                          String orderComment) {

        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerMetroStation = customerMetroStation;
        this.customerPhoneNumber = customerPhoneNumber;
        this.rentalDate = rentalDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.orderComment = orderComment;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getOrderDetails() {
        return new Object[][] {
                {"Иван", "Петров", "Москва", "Алтуфьево", "+79293332255", "05.09.2022", "сутки", "серая безысходность", "нет"},
                {"Ирина", "Иванова", "Москва", "Сокольники", "+79212341222", "07.09.2022", "двое суток", "чёрный жемчуг", "полный заряд"},
                {"12345", "Петров", "Москва", "", "+79293334455", "05.09.2022", "сутки", "серая безысходность", "нет"},

        };
    }

    @Test
    public void checkOrderUpperOrderButtonTest() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();
        objScooterHomePage.clickUpperOrderButton();

        enterOrderDetails();

    }

    @Test
    public void checkOrderBottomOrderButtonTest() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();
        objScooterHomePage.clickBottomOrderButton();

        enterOrderDetails();

    }

    private void enterOrderDetails(){

        ScooterCustomerDetailsPage objScooterCustomerDetailsPage = new ScooterCustomerDetailsPage(driver);
        boolean isCustomerDetailsFilledWithNoErrors = objScooterCustomerDetailsPage.inputCustomerDetails(customerName, customerSurname, customerAddress, customerMetroStation, customerPhoneNumber);

        Assert.assertTrue("Данные о заказчике сервиса введены с ошибками", isCustomerDetailsFilledWithNoErrors);

        ScooterOrderDetailsPage objScooterOrderDetailsPage = new ScooterOrderDetailsPage(driver);
        boolean isOrderDetailsFilledWithNoErrors = objScooterOrderDetailsPage.inputOrderDetails(rentalDate, rentalPeriod, scooterColor, orderComment);

        Assert.assertTrue("Данные заказа введены с ошибками", isOrderDetailsFilledWithNoErrors);

        OrderConfirmationPage objOrderConfirmationPage = new OrderConfirmationPage(driver);
        boolean isOrderConfirmed = objOrderConfirmationPage.clickYesToConfirmOrder();

        Assert.assertTrue("Заказ не подтвержден", isOrderConfirmed);

        OrderStatusPage objOrderStatusPage = new OrderStatusPage(driver);
        boolean isOrderStatusConfirmed = objOrderStatusPage.checkOrderStatusPage();

        Assert.assertTrue("Ошибка при оформлении заказа", isOrderStatusConfirmed);

    }

}
