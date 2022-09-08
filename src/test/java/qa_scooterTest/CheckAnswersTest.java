package qa_scooterTest;

import org.junit.Test;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa_scooter.ScooterHomePage;

@RunWith(Parameterized.class)
public class CheckAnswersTest extends BaseUITest {

    //Поля тестового класса
    private final int questionNumber;
    private final String correctAnswer;

    //Конструктор тестового класса
    public CheckAnswersTest(int questionNumber, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.correctAnswer = correctAnswer;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][] {
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkAnswers() {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openHomePage();

        boolean isAnswerAsExpected = objScooterHomePage.checkThatAnswerIsAsExpected(questionNumber, correctAnswer);

        Assert.assertTrue(getAnswerNotAsExpectedErrorMessage(questionNumber), isAnswerAsExpected);
    }

    private String getAnswerNotAsExpectedErrorMessage(int questionNumber) {
        return String.format("Раздел «Вопросы о важном», вопрос №%s. "
                + "Текст ответа на вопрос не соответствует ожидаемому.", questionNumber);
    }

}
