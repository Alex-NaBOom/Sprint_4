package ru.practikum.yandex.ru.model;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainOrderPage {
    private static final String PAGE_ORDER_URL = "https://qa-scooter.praktikum-services.ru/order"; //Адрес страницы сайта для создания заказа
    private static final By NAME_FIELD = By.xpath(
            "//*[@placeholder = '* Имя']"); // поле "* Имя"
    private static final By LAST_FIELD = By.xpath(
            "//*[@placeholder = '* Фамилия']");  // поле "* Фамилия"

    private static final By ADDRESS_FIELD = By.xpath(
            "//*[@placeholder = '* Адрес: куда привезти заказ']"); // поле "* Адрес: куда привезти заказ"

    private static final By STATION_METRO_FIELD = By.xpath(
            "//*[@placeholder = '* Станция метро']"); // поле "* Станция метро"

    private static final By SELECT_METRO_FROM_CHERKIZOVSKAYA_LIST = By.xpath(".//div[text()='Черкизовская']"); // выбор "станции метро" из списка (Черкизовская)

    private static final By PHONE_NUMBER_FIELD = By.xpath(
            "//*[@placeholder = '* Телефон: на него позвонит курьер']"); // поле "* Телефон: на него позвонит курьер"

    private static final By FURTHER_BUTTON =  By.cssSelector("div.Order_NextButton__1_rCA > button"); // кнопка "Далее"
    private static final By WHEN_TO_BRING_A_SCOOTER_FIELD = By.xpath("//*[@placeholder = '* Когда привезти самокат']"); // поле "* Когда привезете самокат"
    private static final By RENTAL_PERIOD_FIELD = By.xpath("//div[@class = 'Dropdown-placeholder'][text()='* Срок аренды']" ); // поле "*Срок аренды"  "//*[@class=\"Dropdown-root\"]"
    private static final By RENTAL_PERIOD_IN_DAY_FIELD = By.xpath(
            "//div[@class = 'Dropdown-option'][text()='четверо суток']"); // выпадающий список поля "* Срок аренды" выпадающий список выбор кол-ва [суток]

    private static final By ORDER_CHECKOUT_BUTTON = By.xpath(
            "//*[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]"); // Кнопке "Заказать" подтверждение заказа
    private static final By ORDER_CONFIRMATION_BUTTON = By.xpath(
            "//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM'][text()='Да']"); // Кнопка "Да" (Хотите оформить заказ?)

    private static final By ORDER_CREATION_WINDOW = By.xpath("//div[@class = 'Order_Modal__YZ-d3']/div[@class = 'Order_ModalHeader__3FDaJ'][text() = 'Заказ оформлен']"); // всплывающее окно "Заказ оформлен"
    private static final By SELECT_COLOR_SCOOTER_BLACK = By.cssSelector("#black");// выбор цвета самоката "чёрный жемчуг"
    private static final By SELECT_COLOR_SCOOTER_GREY = By.cssSelector("#grey");// выбор цвета самоката "серая безысходность"
    private static final By COMMENT_ORDER = By.cssSelector("div.Order_Form__17u6u > div.Input_InputContainer__3NykH > input");//поле комментарии в заказе
    private final WebDriver driver;
    public MainOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() { // это нужно если делать доп задание
    driver.get(PAGE_ORDER_URL);
           }


    public void fillingFieldFistName(String name) {
        driver.findElement(NAME_FIELD).click();
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(name);
    }
    public void fillingFieldSurname(String surname) {
        driver.findElement(LAST_FIELD).click();
        driver.findElement(LAST_FIELD).clear();
        driver.findElement(LAST_FIELD).sendKeys(surname);
    }
    public void fillingFieldAdress(String adress) {
        driver.findElement(ADDRESS_FIELD).click();
        driver.findElement(ADDRESS_FIELD).clear();
        driver.findElement(ADDRESS_FIELD).sendKeys(adress);
    }

    public MainOrderPage fillingFieldMetroStation() {
        driver.findElement(STATION_METRO_FIELD).click();
        driver.findElement(STATION_METRO_FIELD).clear();
        driver.findElement(SELECT_METRO_FROM_CHERKIZOVSKAYA_LIST).click();
        return this;

    }
    public void fillingFieldPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_FIELD).click();
        driver.findElement(PHONE_NUMBER_FIELD).clear();
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
    }
    public MainOrderPage clickButtonNext() {
        WebElement element = driver.findElement(FURTHER_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(FURTHER_BUTTON).click();
        return this;
    }
    public void fillingFieldWhen(String when){
        driver.findElement(WHEN_TO_BRING_A_SCOOTER_FIELD).clear();
        driver.findElement(WHEN_TO_BRING_A_SCOOTER_FIELD).sendKeys(when);
        driver.findElement(WHEN_TO_BRING_A_SCOOTER_FIELD).sendKeys(Keys.ENTER);
    }
    public MainOrderPage fillingFieldRentTerm(){
        driver.findElement(RENTAL_PERIOD_FIELD).click();
        driver.findElement(RENTAL_PERIOD_IN_DAY_FIELD).click();
        return this;
    }
    public MainOrderPage filingFieldColorBlack(){
        driver.findElement(SELECT_COLOR_SCOOTER_BLACK).click();
        return this;
    }
    public MainOrderPage filingFieldColorGrey(){
        driver.findElement(SELECT_COLOR_SCOOTER_GREY).click();
        return this;
    }
    public void filingFieldComment(String textComment){
        driver.findElement(COMMENT_ORDER).clear();
        driver.findElement(COMMENT_ORDER).sendKeys(textComment);
     }
    public MainOrderPage clickButtonOrder(){
        driver.findElement(ORDER_CHECKOUT_BUTTON).click();
        return this;
    }
    public MainOrderPage clickButtonConfirm(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ORDER_CONFIRMATION_BUTTON));
        driver.findElement(ORDER_CONFIRMATION_BUTTON).click();
        return this;
    }

    public MainOrderPage checkingOrderCreation(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_CREATION_WINDOW));
        boolean isDisplayed = driver.findElement(ORDER_CREATION_WINDOW).isDisplayed();
        Assert.assertTrue(isDisplayed);
        return this;
       }
}
