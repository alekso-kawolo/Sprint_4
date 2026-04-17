package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {

    private final By cookieButton = By.id("rcc-confirm-button");
    private final By headerOrderButton = By.xpath("//button[contains(@class,'Button_Button__ra12g') and text()='Заказать']");
    private final By bottomOrderButton = By.cssSelector("[class^='Home_FinishButton__'] .Button_Button__ra12g");
    private final By accordionItems = By.cssSelector("[class^='Home_FAQ__'] .accordion__item");
    private final By accordionHeading = By.cssSelector(".accordion__heading");
    private final By accordionPanel = By.cssSelector(".accordion__panel");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public OrderPage clickHeaderOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
        return new OrderPage(driver);
    }

    public OrderPage clickBottomOrderButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        return new OrderPage(driver);
    }

    public void openQuestion(int index) {
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(accordionItems));
        WebElement question = items.get(index).findElement(accordionHeading);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getAnswerText(int index) {
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(accordionItems));
        WebElement answer = items.get(index).findElement(accordionPanel);
        wait.until(ExpectedConditions.visibilityOf(answer));
        return answer.getText();
    }
}
