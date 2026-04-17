import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccordionTests extends BaseUITest {

    private static final String[] QUESTIONS = {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я живу за МКАДом, привезёте?"
    };

    private static final String[] ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    private final int index;

    public AccordionTests(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "FAQ #{0}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[QUESTIONS.length][1];
        for (int i = 0; i < QUESTIONS.length; i++) {
            data[i] = new Object[]{i};
        }
        return Arrays.asList(data);
    }

    @Test
    public void testAccordionQuestion() {
        MainPage mainPage = new MainPage(driver).open();
        mainPage.acceptCookies();

        mainPage.openQuestion(index);

        assertEquals(ANSWERS[index], mainPage.getAnswerText(index));
    }
}
