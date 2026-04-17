package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroSuggestion = By.cssSelector(".select-search__select");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By rentalPeriodMenu = By.cssSelector(".Dropdown-menu");
    private final By blackColorCheckbox = By.xpath("//label[contains(text(),'чёрный жемчуг')]");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    private final By yesButton = By.xpath("//button[contains(text(),'Да')]");
    private final By orderModalHeader = By.cssSelector("div.Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void fillFirstStep(String name, String surname, String address, String metro, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        WebElement metroInput = driver.findElement(metroField);
        metroInput.sendKeys(metro);
        wait.until(ExpectedConditions.elementToBeClickable(metroSuggestion)).click();

        driver.findElement(phoneField).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    public void fillSecondStep(String date, String rentalPeriod, boolean isBlackColor, String comment) {
        driver.findElement(dateField).sendKeys(date + Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodMenu));
        driver.findElement(By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + rentalPeriod + "']")).click();

        if (isBlackColor) {
            wait.until(ExpectedConditions.elementToBeClickable(blackColorCheckbox)).click();
        }

        driver.findElement(commentField).sendKeys(comment);
    }

    public boolean createOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(orderModalHeader, "Заказ оформлен"));
    }
}
