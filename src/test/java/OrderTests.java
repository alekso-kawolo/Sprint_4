import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;
import pageobject.OrderPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests extends BaseUITest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final boolean isBlackColor;
    private final String comment;
    private final boolean useTopButton;

    public OrderTests(String name, String surname, String address, String metro, String phone,
                      String date, String rentalPeriod, boolean isBlackColor, String comment,
                      boolean useTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.isBlackColor = isBlackColor;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }

    @Parameterized.Parameters(name = "{index}: {0} {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Вася", "Пупкин", "Москва", "ВДНХ", "+71234567890", "11.04.2026", "сутки", true, "Привет", true},
                {"Иван", "Иванов", "Москва", "Юго-Западная", "+79991234567", "15.04.2026", "двое суток", false, "Без звонка", false}
        });
    }

    @Test
    public void testOrderFlow() {
        MainPage mainPage = new MainPage(driver).open();
        mainPage.acceptCookies();

        OrderPage orderPage = useTopButton
                ? mainPage.clickHeaderOrderButton()
                : mainPage.clickBottomOrderButton();

        orderPage.fillFirstStep(name, surname, address, metro, phone);
        orderPage.fillSecondStep(date, rentalPeriod, isBlackColor, comment);

        assertTrue(orderPage.createOrder());
    }
}
